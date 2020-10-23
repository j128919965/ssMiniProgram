const app = getApp();
const {
  API_ST_GROUP,
  API_ST_SCAN_CARD,
  API_ST_SAVE_LIST,
  API_ST_GET_CARDS
} = require('../../../utils/urls');
const {
  post,
  get
} = require('../../../utils/util');

Page({
  data: {
    touch: false,
    gid: -1,
    master: false,
    list: [],
    cardInfo: [{
        name: "海潮涌动",
        point: 200,
        prop: 1
      },
      {
        name: "烈焰焚尽",
        point: 300,
        prop: 2
      },
      {
        name: "冰锥术",
        point: 100,
        prop: 3
      },
      {
        name: "闪电链",
        point: 300,
        prop: 4
      },
      {
        name: "奥数冲击",
        point: 300,
        prop: 5
      }
    ],
    cardType: [{
        color: "#56B9F3",
        icon: "iconfenzu"
      }, //水
      {
        color: "#E96F4D",
        icon: "iconhot"
      }, //火
      {
        color: "#90D8DB",
        icon: "iconicon16"
      }, //冰
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
    let uData = app.globalData.uData;
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
            content: "这张卡片已经扫过了哦"
          })
        }
      }
    })
  },
  async saveBtn(){
    let data = await this.save();
    if(data){
      wx.showToast({
        title: '保存成功！',
        icon:"success"
      })
    }
  },
  async save() {
    return await post(API_ST_SAVE_LIST, {
      list: this.data.list.map(e => e.id),
      gid: this.data.gid
    })
  },
  init(){
    if(this.data.gid<0 || this.data.list.length<1)return;
    this.getwxmlcode("#movebox", (container) => {
			this.setData({
				movebox: container
			})
			setTimeout(() => {
				this.getwxmlcode("#movelist0", (firstitem) => {
					this.setData({
						movelist0: firstitem
          })
					var jiange = firstitem.top - container.top;
          var yiban = 50 + jiange/2;
          console.log(firstitem.bottom - firstitem.top)
          console.log("movebox top : " + container.top + ' list0 top : '+firstitem.top)
					this.setData({
						itemheight: 50 ,
						jiange: yiban, //两条中间到另一条的距离
						jianqu: container.top - 25, //位置要减去距离
					})
				})
			}, 300)
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
      var movetop = e.touches[0].pageY - this.data.itemheight;
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
    console.log("press")
    this.setData({touch:true})
  }
})