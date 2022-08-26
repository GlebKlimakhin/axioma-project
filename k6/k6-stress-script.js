import http from 'k6/http';
import {sleep} from 'k6'

export const options = {
    stages : [
        {duration : '5m', target: 10},
        {duration : '7m', target: 50},
        {duration : '8m', target: 100},
        {duration : '5m', target: 70},
        {duration : '3m', target: 30},
        {duration : '2m', target : 0}
    ]
};

export default function () {
    const urlToLogin = 'http://localhost:8189/auth/login';
    const urlToUser = 'http://localhost:8189/users/1';

    const payload = JSON.stringify({
        username: 'user1',
        password: 'user1'
    });

    const params = {
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        },
    };

    let loginPost = http.post(urlToLogin, payload, params);
    let token = loginPost.body('token');

    const paramsWithToken = {
        headers: {
            'Accept': 'application/json',
            'Authorization' : token
        },
    };
    let usersRequest = http.get(urlToUser, paramsWithToken);

    sleep(1);
}