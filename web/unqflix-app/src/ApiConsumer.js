import axios from 'axios';
import Cookies from 'js-cookie';

const instance = axios.create({
  baseURL: "http://localhost:7000/",
  withCredentials: false,
  headers: {
    'Access-Control-Allow-Origin': '*',
    'Access-Control-Allow-Methods': 'GET,PUT,POST,DELETE,PATCH,OPTIONS',
    'Authorization': Cookies.get('authToken'),
  }
});

const updateAuthToken = () => instance.defaults.headers.common['Authorization'] = Cookies.get('authToken');

// {
//   "name": "Edward Elric",
//   "email": "edwardElric@gmail.com",
//   "password": "philosopherStone",
//   "image": "https://a.wattpad.com/cover/83879595-352-k192548.jpg",
//   "creditCard": "4444 3333 2222 1111"
// }

//posts
const register = ({name, email, password, image, creditCard}) => instance.post('register', {name, email, password, image, creditCard});
const login = ({email, password}) => instance.post('/login', {email, password});
const addLastSeen = (contentId) => instance.post('/user/lastSeen', {id: contentId});
const addFav = (contentId) => instance.post('/user/fav', {id: contentId});
//gets
const search = (searchText) => instance.get('/search', {params: {text: searchText}}); // otra opciones es pasar {params: {text: searchText}}
const getContent = (contentId) => instance.get(`/content/${contentId}`);
const getUserContent = () => instance.get('/user');
const getAvailableContent = () => instance.get('/content');
const getBanners = () => instance.get('/banners');

export default {
  register,
  login,
  addLastSeen,
  addFav,
  search,
  getContent,
  getUserContent,
  getAvailableContent,
  getBanners,
  updateAuthToken,
};