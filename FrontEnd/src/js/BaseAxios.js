import axios from 'axios';
const token = localStorage.getItem('accessToken');
function localaxios () {
    const instance = axios.create({
        baseURL: "http://192.168.31.57:8080",

        headers:{
            "Content-Type": "application/json;charset=utf-8",
        },
        "Access-Control-Allow-Origin": "http://192.168.31.57:8080",
        "Access-Control-Allow-Credentials":true,
        withCredentials:true
    })
    instance.defaults.headers.common["Authorization"]=`Bearer ${token}`;
    instance.defaults.headers.post["Content-Type"]="application/json";
    instance.defaults.headers.put["Content-Type"]="application/json"
    return instance;
}

export { localaxios, }