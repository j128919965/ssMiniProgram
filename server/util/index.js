const db = require('./db')

const myCall = (sql,data)=>{
	return new Promise((resolve,reject)=>{
		db.procedureSQL(sql, JSON.stringify(data), (err, ret) => {
			if (err) {
				console.log('*****************************************************************************************');
				console.log(`* ${new Date().toUTCString()}`);
				console.log(`An error occurred when excute the sql ' ${sql} '. please checkit .`);
				console.log('*****************************************************************************************');
				reject(err)
			} else {
				resolve(ret)
			}
		})
	})
}


const callProc = (sql, params, res, cb)=>{
	db.procedureSQL(sql, JSON.stringify(params), (err, ret) => {
		if (err) {
			res.status(500).json({code: -1, msg: '提交请求失败，请联系管理员！', data: null})
		} else {
			cb(ret)
		}
	})
}
//多参数传出，传出数据冗余较大
//Callbacknum = 希望获得的数据集数
const callProc_N = (sql, params,Callbacknum, res, cb)=>{
	//console.log(params)
	db.procSQL_MutiCB(sql, JSON.stringify(params),Callbacknum, (err, ret) => {
		if (err) {
			res.status(500).json({code: -1, msg: '提交请求失败，检查传入参数data', data: params})
		} else {
			cb(ret)
		}
	})
}

//循环加入表，按照json数组传入[{"table:"插入的表单","fieldList":要插入的数据属性列表（比如["type"，"name"，"id"]）,"valLsit":[与属性列表对应的数据列表]}]
const easyAdd_N =(params,res,cb)=>{
	console.log(params);
	params.map((item,index)=>{
		db.easyadd(item.table,item.fieldList,item.valLsit,(err, ret) => {
			if (err) {
				res.status(500).json({code: -1, msg: '提交请求失败，检查传入参数data', data: params})
			} else {
				cb(ret)
			}
		})
	})
}


//easyadd的升级版 传入一个固定table fieldlist 传入json数组["valLsit":[与属性列表对应的数据列表]}]
const SUPER_easyAdd_N =(table,fieldList,params,res,cb)=>{
	console.log(params);
	params.map((item,index)=>{
		db.easyadd(table,fieldList,item.valLsit,(err, ret) => {
			if (err) {
				res.status(500).json({code: -1, msg: '提交请求失败，检查传入参数data', data: params})
			} else {
				cb(ret)
			}
		})
	})
}
module.exports = {
	callProc,
	callProc_N,
	easyAdd_N,
	SUPER_easyAdd_N,
	myCall
}