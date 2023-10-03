import axios from 'axios';

// const http = axios.create({
//     baseURL: "http://localhost:3000"
// });

export default {

    newBand(band, userId) {
        return axios.post(`/bands/create/${userId}`, band);
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

    getBandCoverImage(bandId) {
        return axios.get(`/coverphoto/${bandId}`)
    },

    searchBands(searchTerms) {
        return axios.get(`/bands/search/${searchTerms}`)
    },

    isUserFollowing(bandId, userId) {
        return axios.get(`/bands/${bandId}/users/${userId}/isfollowing`)
    },

    followGroup(bandId, userId) {
        return axios.post(`/bands/${bandId}/follow/${userId}`)
    },
    
    unfollowGroup(bandId, userId) {
        return axios.delete(`/bands/${bandId}/unfollow/${userId}`)
    },
    getAllUserFollowing(userId) {
        return axios.get(`/bands/following/${userId}`)
    },
    getBandOwnerIdByBandId(bandId){
        return axios.get(`/bands/${bandId}/owner`);
    },
    getBandsByOwnerId(userId) {
        return axios.get(`/bands/ownedbands/${userId}`)
    }
    
    // isUserFollowing(){
    //     return axios.get(`/bands/following/`)
    // }
}