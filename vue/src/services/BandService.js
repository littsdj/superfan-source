import axios from 'axios';

// const http = axios.create({
//     baseURL: "http://localhost:3000"
// });

export default {

    newBand(band) {
        return axios.post('/bands/create', band);
        //return http.post('/bands/create', band);
    },

    getBand(bandName) {
        return axios.get(`/bands/${bandName}`)
    },

    postBandCoverImage(bandId, formData) {
        const options = {
            headers: {
            'Content-Type': 'multipart/form-data'
            }
        }
        return axios.post(`/coverphoto/${bandId}`, formData, options)
    },

    getBandCoverImage(bandId){
        return axios.get(`/coverphoto/${bandId}`)
    },
    searchBands(searchParams) {
        return axios.get(``)
    }
}