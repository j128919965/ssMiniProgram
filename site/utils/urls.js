const LOCAL_SERVER = 'http://localhost';
//const SERVER_HOST = 'https://ssacgn.online/api';
const SERVER_HOST = 'http://localhost:8080/api';

module.exports={
  API_LOGIN: SERVER_HOST + '/login',//1
  API_GET_MESSAGE_COUNT: SERVER_HOST + '/message/notReadCount',//1
  API_MESSAGE: SERVER_HOST + '/message',//1
  API_READ_MSG: SERVER_HOST + '/message/readMsg',//1
  API_HONOR:SERVER_HOST + '/honor',//1
  API_NOTICE:SERVER_HOST + '/notice',//1
  API_USER:SERVER_HOST + '/user',//1
  API_ACTIVITY:SERVER_HOST + '/activity',//1
  API_POST_SIGNUP:SERVER_HOST + '/activity/signup',//1
  API_GET_HAS_SIGNED:SERVER_HOST + '/activity/ifSigned',//1
  API_SWIPER:SERVER_HOST + '/notice/swiper',//1
  API_ST_GROUP:SERVER_HOST + '/st/group',
  API_ST_SCAN_CARD:SERVER_HOST + '/st/scan',
  API_ST_SAVE_LIST:SERVER_HOST + '/st/save',
  API_ST_GET_CARDS:SERVER_HOST + '/st/cards'
}
