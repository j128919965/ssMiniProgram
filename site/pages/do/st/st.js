const app = getApp();
const {
  API_ST_GROUP,
  API_ST_SCAN_CARD,
  API_ST_SAVE_LIST,
  API_ST_GET_CARDS,
  API_ST_GET_CARD_INFO,
  API_ST_STATUS,
  API_ST_POINTS
} = require('../../../utils/urls');
const {
  post,
  get
} = require('../../../utils/util');

Page({
  data: {
    st_status:0,
    visiable:false,
    points:[],
    touch: false,
    gid: -1,
    master: false,
    list: [],
    cardInfo: [],
    cardType: [
      {
        color: "#90D8DB",
        icon: "iconicon16"
      }, //冰
      {
        color: "#E96F4D",
        icon: "iconhot"
      }, //火
      {
        color: "#56B9F3",
        icon: "iconfenzu"
      }, //水
      {
        color: "#E2E900",
        icon: "iconleidian"
      }, //电
      {
        color: "#8E87F6",
        icon: "iconicon20"
      } //奥数
    ]
  },
  onLoad: async function (options) {
    let st_status = await get(API_ST_STATUS);
    if(st_status==0)return;
    this.setData({st_status:st_status})
    if(st_status==2){
      let points = await get(API_ST_POINTS);
      this.setData({points:points})
      return;
    }
    let uData = app.globalData.uData;
    let cardInfos = app.globalData.cardInfos;
    if(!cardInfos){
      cardInfos = await get(API_ST_GET_CARD_INFO);
      app.globalData.cardInfos = cardInfos;
    }
    this.setData({cardInfo:cardInfos})
    if (!uData) {
      app.callbacks.push((data) => {
        this.setData({
          uData: data
        })
        this.getInfos();
      })
    } else {
      this.setData({
        uData: uData
      })
      this.getInfos();
    }
  },
  // onPullDownRefresh:function(){
  //   if(this.data.gid==-1)return;
  //   this.getCards();
  // },
  async getInfos() {
    let uid = this.data.uData.uid;
    let groupSelect = await get(API_ST_GROUP, {
      uid: uid
    });
    if (!!groupSelect) {
      this.setData({
        gid: groupSelect.gid,
        master: groupSelect.master
      })
      await this.getCards();
      this.init();
    }
  },
  async getCards() {
    let cards = await get(API_ST_GET_CARDS, {
      gid: this.data.gid
    })
    this.setData({
      list: cards
    });
  },
  scan() {
    if(!this.data.master)return;
    let that = this;
    let data = wx.scanCode({
      onlyFromCamera: false,
      async success(d) {
        try {
          data = JSON.parse(d.result);
        } catch (e) {
          console.log("扫码失败")
          wx.showModal({
            showCancel: false,
            confirmText: "好",
            title: '失败！',
            content: "请扫描sunshine动漫社的二维码哦"
          })
          return;
        }
        if (!data.id) {
          wx.showModal({
            showCancel: false,
            confirmText: "好",
            title: '失败！',
            content: "请扫描sunshine动漫社的二维码哦"
          })
          return;
        }
        console.log("---gid--- : " + that.data.gid)
        let suc = await post(API_ST_SCAN_CARD, {
          cid: data.id,
          gid: that.data.gid
        });
        if (suc) {
          wx.showToast({
            title: '获取成功！',
            icon: "success"
          })
          let list = that.data.list;
          list.push(data)
          that.setData({
            list: list
          })
          console.log(list.length)
          if (list.length == 1) {
            that.init()
          }

          that.save();
        } else {
          wx.showModal({
            showCancel: false,
            confirmText: "好",
            title: '失败！',
            content: "这张卡片已经扫过了，或该环节已经结束了哦"
          })
        }
      }
    })
  },
  async saveBtn(){
    let data = await this.save();
    console.log(data)
    if(data){
      wx.showToast({
        title: '保存成功！',
        icon:"success"
      })
    }else{
      wx.showToast({
        title: '该环节已结束！',
        icon:"none"
      })
    }
  },
  async save() {
    if(!this.data.master)return;
    return await post(API_ST_SAVE_LIST, {
      list: this.data.list.map(e => e.id),
      gid: this.data.gid
    })
  },
  init(){
    if(this.data.gid<0 || this.data.list.length<1)return;
    this.getwxmlcode("#movelist0", (firstitem) => {
      var yiban = 55 +  firstitem.top/2;
      this.setData({
        jiange: yiban, //两条中间到另一条的距离
        jianqu: - 27.5, //位置要减去距离
      })
    })
  },
  getwxmlcode(str, cal) {
		const query1 = wx.createSelectorQuery()
		query1.select(str).boundingClientRect()
		query1.selectViewport().scrollOffset()
		query1.exec((res) => {
			if (cal) cal(res[0])
		})
	},
  listitemmove(e) {
    if(!this.data.touch)return;
		if (e.type == "touchmove") {
      var movetop = e.touches[0].pageY - 55;
      var moveoutindex = parseInt((movetop - this.data.jianqu) / this.data.jiange);
			if (e.currentTarget.dataset.index <= moveoutindex) moveoutindex++;
      this.moveoutindex = moveoutindex;
			this.setData({
				nowmoveindex: e.currentTarget.dataset.index,
				movetop,
				moveoutindex
			})
		} else {
			let index = e.currentTarget.dataset.index,
				score = this.data.list;
			let data = {
				...score[index]
			};
			score.splice(index, 1);
			if (index <= this.moveoutindex - 1) this.moveoutindex--;
			score.splice(this.moveoutindex, 0, data);
			this.setData({
				list: score,
				moveoutindex: -1,
        nowmoveindex: -1,
        touch:false
			});
		}
  },
  press(){
    wx.vibrateShort()
    this.setData({touch:true})
  },
  showModal:function(e){
    this.setData({visiable:true})
  },
  hideModal:function(){
    this.setData({visiable:false})
  }
})