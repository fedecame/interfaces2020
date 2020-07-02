import Cookie from 'js-cookie';
import apiConsumer from './ApiConsumer';

class Auth {
    constructor() {
        this.authenticated = Cookie.get("authToken");
    }

    login(token){
        this.authenticated = true;
        Cookie.set("authToken", token);
        apiConsumer.updateAuthToken();
    }

    logout(){
        this.authenticated = false;
        Cookie.remove("authToken");
        apiConsumer.updateAuthToken(); // es necesario?
    }

    isAuthenticated(){
        return this.authenticated;
    }
}
 
export default new Auth();