import http from '../utils/http';

// 专题接口
export const topicApi = {
  // 获取专题列表
  getTopicList: (pageNo: number = 1, pageSize: number = 10, keyword?: string) => {
    return http.get('/json-import/list', {
      params: {
        pageNo,
        pageSize,
        keyword
      }
    });
  },
  getTopicDetail: (id: string | number) => {
    return http.get('/json-import/detail', {
      params: {
        id
      }
    });
  },
};

export default topicApi;