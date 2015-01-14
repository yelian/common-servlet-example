ChouJiangObj = function(){};

//奖品总数
ChouJiangObj.prototype.totalAward = null;

//抽奖人员
ChouJiangObj.prototype.toAwardPersons = new Array();

//中奖人员
ChouJiangObj.prototype.awardedPersons = new Array();

ChouJiangObj.prototype.setAwardPerson = function(awardPerson){
	this.awardedPersons.push(awardPerson);
}