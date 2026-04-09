<script setup lang="ts">
import { computed, onMounted, ref } from 'vue'
import { ElMessage } from 'element-plus'
import request from './utils/request'

interface JsonImportRecord {
  id: number
  jsonData: string
  createTime: string
  updateTime: string
}

interface BackendPage<T> {
  records: T[]
  total: number
  size: number
  current: number
}

interface TopicCard {
  id: number
  title: string
  summary: string
  browseCount: number
  cover: string
  updateTime: string
}

const keyword = ref('')
const loading = ref(false)
const records = ref<TopicCard[]>([])
const pageNo = ref(1)
const pageSize = ref(6)
const total = ref(0)

const formatNumber = (num: number) => {
  if (num >= 10000) return `${(num / 10000).toFixed(1)} 万`
  return `${num}`
}

const formatTime = (time: string) => {
  if (!time) return '刚刚'
  return time.replace('T', ' ')
}

const stats = computed(() => ({
  topicCount: total.value,
  readingCount: formatNumber(total.value * 18241)
}))

const toObject = (raw: string): Record<string, unknown> => {
  try {
    const parsed = JSON.parse(raw)
    if (typeof parsed === 'object' && parsed !== null && !Array.isArray(parsed)) {
      return parsed as Record<string, unknown>
    }
    return { value: parsed }
  } catch {
    return {}
  }
}

const toStringValue = (obj: Record<string, unknown>, keys: string[], fallback: string) => {
  for (const key of keys) {
    const value = obj[key]
    if (typeof value === 'string' && value.trim()) {
      return value.trim()
    }
  }
  return fallback
}

const toNumberValue = (obj: Record<string, unknown>, keys: string[], fallback: number) => {
  for (const key of keys) {
    const value = obj[key]
    if (typeof value === 'number' && Number.isFinite(value)) {
      return value
    }
    if (typeof value === 'string' && value.trim() && !Number.isNaN(Number(value))) {
      return Number(value)
    }
  }
  return fallback
}

const mapToTopicCard = (record: JsonImportRecord): TopicCard => {
  const jsonObj = toObject(record.jsonData)
  const fallbackTitle = `专题讨论 #${record.id}`

  return {
    id: record.id,
    title: toStringValue(jsonObj, ['title', 'question', 'topicTitle', 'name'], fallbackTitle),
    summary: toStringValue(
      jsonObj,
      ['introduction', 'summary', 'content', 'description', 'detail', 'excerpt'],
      '暂无简介'
    ),
    browseCount: toNumberValue(
      jsonObj,
      ['view_count', 'viewCount', 'browseCount', 'views', 'readCount'],
      800000 + record.id * 2391
    ),
    cover: toStringValue(
      jsonObj,
      ['banner', 'bannerUrl', 'banner_url', 'cover', 'image', 'thumbnail'],
      ''
    ),
    updateTime: record.updateTime
  }
}

const fetchTopics = async () => {
  loading.value = true
  try {
    const data = (await request.get('/json-import/list', {
      params: {
        pageNo: pageNo.value,
        pageSize: pageSize.value,
        keyword: keyword.value.trim() || undefined
      }
    })) as BackendPage<JsonImportRecord>

    const list = Array.isArray(data?.records) ? data.records : []
    records.value = list.map(mapToTopicCard)
    total.value = data?.total ?? 0
  } catch {
    ElMessage.error('专题数据加载失败，请确认后端服务已启动')
  } finally {
    loading.value = false
  }
}

const onSearch = () => {
  pageNo.value = 1
  fetchTopics()
}

const onPageChange = (page: number) => {
  pageNo.value = page
  fetchTopics()
}

onMounted(() => {
  fetchTopics()
})
</script>

<template>
  <div class="topic-page">
    <div class="topic-panel">
      <div class="topic-head">
        <div class="head-left">
          <span class="head-title">全部专题</span>
          <span class="head-count">共有 {{ stats.topicCount }} 个专题</span>
        </div>
        <div class="search-wrap">
          <el-input
            v-model="keyword"
            placeholder="搜索专题标题"
            clearable
            @keyup.enter="onSearch"
            @clear="onSearch"
          />
        </div>
      </div>
      <div class="head-subtitle">专题累计浏览 {{ stats.readingCount }}</div>

      <div class="topic-list" v-loading="loading">
        <template v-if="records.length">
          <article v-for="item in records" :key="item.id" class="topic-item">
            <div class="topic-cover">
              <img
                :src="
                  item.cover ||
                  'https://images.unsplash.com/photo-1469474968028-56623f02e42e?auto=format&fit=crop&w=400&q=80'
                "
                alt="cover"
              />
            </div>
            <div class="topic-main">
              <h3>{{ item.title }}</h3>
              <div class="topic-meta">{{ formatTime(item.updateTime).slice(5, 10) }} 更新</div>
              <p>{{ item.summary }}</p>
            </div>
            <div class="topic-action">
              <el-button class="follow-btn" type="primary" plain>关注专题</el-button>
              <div class="browse">{{ formatNumber(item.browseCount) }} 次浏览</div>
            </div>
          </article>
        </template>
        <el-empty v-else description="暂无专题数据" />
      </div>

      <div class="pager">
        <el-pagination
          background
          layout="prev, pager, next"
          :total="total"
          :page-size="pageSize"
          :current-page="pageNo"
          @current-change="onPageChange"
        />
      </div>
    </div>
  </div>
</template>
