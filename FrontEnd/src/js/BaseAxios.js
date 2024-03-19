import axios from 'axios';
const token = localStorage.getItem('token');
function localaxios () {
    const instance = axios.create({
        baseURL: "https://j10b303.p.ssafy.io",

        headers:{
            "Content-Type": "application/json;charset=utf-8",
        },
        "Access-Control-Allow-Origin": "https://j10b303.p.ssafy.io",
        "Access-Control-Allow-Credentials":true,
        withCredentials:true
    })
    instance.defaults.headers.common["Authorization"]=`Bearer ${token}`;
    instance.defaults.headers.post["Content-Type"]="application/json";
    instance.defaults.headers.put["Content-Type"]="appication/json"
    return instance;
}

export { localaxios, }