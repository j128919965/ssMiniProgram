// const SERVER_HOST = 'http://localhost';
const SERVER_HOST = 'http://ss.lizhaorong.xyz';

module.exports={
  API_LOGIN: SERVER_HOST + '/login',
  API_GET_MESSAGE_COUNT: SERVER_HOST + '/message/getNoreadCount',
  API_GET_MESSAGE_LIST: SERVER_HOST + '/message/getMessages',
  API_POST_READ_MSG: SERVER_HOST + '/message/postReadMsg'
}
