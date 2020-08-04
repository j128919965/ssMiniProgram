const LOCAL_SERVER = 'http://localhost';
const SERVER_HOST = 'https://lizhaorong.xyz';

module.exports={
  API_LOGIN: SERVER_HOST + '/login',
  API_GET_MESSAGE_COUNT: SERVER_HOST + '/message/getNoreadCount',
  API_GET_MESSAGE_LIST: SERVER_HOST + '/message/getMessages',
  API_POST_READ_MSG: SERVER_HOST + '/message/postReadMsg',
  API_GET_HONORS:SERVER_HOST + '/honor/getHonors',
  API_GET_NOTICE:SERVER_HOST + '/notice/getNotice',
  API_POST_UDATA:SERVER_HOST + '/updateUser',
  API_GET_ACT_BY_ID:SERVER_HOST + '/activity/getActivityByID',
  API_POST_SIGNUP:LOCAL_SERVER + '/activity/signup',
  API_GET_HAS_SIGNED:LOCAL_SERVER + '/activity/getHasSigned'
}
