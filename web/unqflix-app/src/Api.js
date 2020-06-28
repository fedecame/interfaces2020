import axios from 'axios';

const server = 'https://localhost:7000';

const request = (type, path, body) => axios
.request({url:`${server}${path}`, method: type, data: body})
.then(req => req.data);

export const login = body => request('post','/login', body);
export const userRegister = body => request('post', '/register', body);
export const getFAvoritesAndLastSeen = body => request('get','/user', body);
export const getAvailableOrderByTitle = body => request('get', '/content', body);
export const getBanners = body => request('get', '/banners', body);
export const searchByText = body => request('get', '/search?text={text}', body);
 