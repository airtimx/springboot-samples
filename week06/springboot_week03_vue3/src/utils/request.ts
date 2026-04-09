import axios, { type AxiosError, type AxiosResponse, type InternalAxiosRequestConfig } from 'axios'

const request = axios.create({
  baseURL: '/api',
  timeout: 10000,
  headers: {
    'Content-Type': 'application/json'
  }
})

request.interceptors.request.use(
  (config: InternalAxiosRequestConfig) => {
    // 如果后续接入登录态，可在这里统一注入 token
    return config
  },
  (error: AxiosError) => Promise.reject(error)
)

request.interceptors.response.use(
  (response: AxiosResponse) => response.data,
  (error: AxiosError) => {
    // 统一抛出错误，便于页面层集中处理
    return Promise.reject(error)
  }
)

export default request
