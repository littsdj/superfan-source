import axios from 'axios';

// const http = axios.create({
//     baseURL: "http://localhost:3000"
// });

export default {

    newBand(band) {
        return axios.post('http://localhost:9000/bands/create', band);
        //return http.post('/bands/create', band);
    },

    getBand(bandName) {
        return axios.get(`http://localhost:9000/bands/${bandName}`)
    }

}