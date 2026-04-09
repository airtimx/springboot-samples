type RequestOptions = {
  params?: Record<string, string | number | boolean | null | undefined>;
};

type RequestConfig = {
  url: string;
  method: 'GET' | 'POST' | 'PUT' | 'DELETE';
  data?: unknown;
  params?: Record<string, string | number | boolean | null | undefined>;
};

const BASE_URL = 'http://localhost:8080';

const buildQueryString = (params?: Record<string, string | number | boolean | null | undefined>) => {
  if (!params)
    return '';
  const entries = Object.entries(params)
    .filter(([, value]) => value !== undefined && value !== null && value !== '')
    .map(([key, value]) => `${encodeURIComponent(key)}=${encodeURIComponent(String(value))}`);
  return entries.length ? `?${entries.join('&')}` : '';
};

const request = <T>(config: RequestConfig): Promise<T> => {
  const url = `${BASE_URL}${config.url}${buildQueryString(config.params)}`;
  return new Promise((resolve, reject) => {
    uni.request({
      url,
      method: config.method,
      data: config.data,
      timeout: 10000,
      dataType: 'text',
      header: {
        'Content-Type': 'application/json',
      },
      success: (res) => {
        if (res.statusCode >= 200 && res.statusCode < 300) {
          const raw = res.data;
          if (typeof raw === 'string') {
            const text = raw.trim();
            if (text.startsWith('<!DOCTYPE') || text.startsWith('<html')) {
              reject(new Error('Server returned HTML instead of JSON'));
              return;
            }
            try {
              resolve(JSON.parse(text) as T);
              return;
            }
            catch {
              reject(new Error('Response is not valid JSON'));
              return;
            }
          }
          resolve(raw as T);
          return;
        }
        reject(new Error(`Request failed: ${res.statusCode}`));
      },
      fail: (err) => {
        reject(err);
      },
    });
  });
};

const http = {
  get<T = unknown>(url: string, options: RequestOptions = {}) {
    return request<T>({
      url,
      method: 'GET',
      params: options.params,
    });
  },
};

export default http;