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

const tmbdApiKey = "f89a93870b54be100cd02918d7089864";
const tmdbInstance = axios.create({
  baseURL: "https://api.themoviedb.org/3/",
  withCredentials: false,
});

const updateAuthToken = () => instance.defaults.headers.common['Authorization'] = Cookies.get('authToken');

//posts
const register = ({name, email, password, image, creditCard}) => instance.post('register', {name, email, password, image, creditCard});
const login = ({email, password}) => instance.post('/login', {email, password});
const addLastSeen = (contentId) => instance.post('/user/lastSeen', {id: contentId});
const addFav = (contentId) => instance.post('/user/fav', {id: contentId});
//gets
const search = (searchText) => instance.get('/search', {params: {text: searchText}});
const getContent = (contentId) => instance.get(`/content/${contentId}`);
const getUserContent = () => instance.get('/user');
const getAvailableContent = () => instance.get('/content');
const getBanners = () => instance.get('/banners');

//tmdb
const getMovieImage = movieTitle => tmdbInstance.get('/search/movie', {params: {api_key: tmbdApiKey, query: movieTitle}});
const getSerieImage = serieTitle => tmdbInstance.get('/search/tv', {params: {api_key: tmbdApiKey, query: serieTitle}});

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
  getMovieImage,
  getSerieImage,
};