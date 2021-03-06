package com.itheima.pvzhm52.utils;

import org.cocos2d.actions.base.CCAction;
import org.cocos2d.actions.base.CCRepeatForever;
import org.cocos2d.actions.interval.CCAnimate;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.layers.CCTMXObjectGroup;
import org.cocos2d.layers.CCTMXTiledMap;
import org.cocos2d.nodes.CCAnimation;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCNode;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCSpriteFrame;
import org.cocos2d.transitions.CCFlipXTransition;
import org.cocos2d.types.CGPoint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * ============================================================
 * Copyright：Google有限公司版权所有 (c) 2017
 * Author：   陈冠杰
 * Email：    815712739@qq.com
 * GitHub：   https://github.com/JackChen1999
 * 博客：http://blog.csdn.net/axi295309066
 * 微博：AndroidDeveloper
 * <p>
 * Project_Name：pvzhm52
 * Package_Name：com.itheima.pvzhm52
 * Version：1.0
 * time：2017/2/15 11:50
 * des ：工具类
 * gitVersion：$Rev$
 * updateAuthor：$Author$
 * updateDate：$Date$
 * updateDes：${TODO}
 * ============================================================
 **/
public class CommonUtils {
	/**
	 * 切换图层 
	 * @param newLayer  新进入的图层
	 */
	public static void changeLayer(CCLayer newLayer){
		CCScene scene=CCScene.node();
		scene.addChild(newLayer);
		CCFlipXTransition transition=CCFlipXTransition.transition(2, scene, 0);
		CCDirector.sharedDirector().replaceScene(transition);//切换场景 ,参数 新的场景
	}
	/**
	 * 解析地图上 对象的所有点
	 * @param map  地图
	 * @param name  对象的名字
	 * @return
	 */
	public static List<CGPoint> getMapPoints(CCTMXTiledMap map,String name){
		List<CGPoint> points = new ArrayList<CGPoint>();
		// 解析地图
		CCTMXObjectGroup objectGroupNamed = map.objectGroupNamed(name);
		ArrayList<HashMap<String, String>> objects = objectGroupNamed.objects;
		for (HashMap<String, String> hashMap : objects) {
			int x = Integer.parseInt(hashMap.get("x"));
			int y = Integer.parseInt(hashMap.get("y"));
			CGPoint cgPoint = CCNode.ccp(x, y);
			points.add(cgPoint);
		}
		return points;
	}
	/**
	 * 创建了序列帧的动作
	 * @param format  格式化的路径
	 * @param num   帧数
	 * @param isForerver  是否永不停止的循环
	 * @return
	 */
	public  static CCAction getAnimate(String format,int num,boolean isForerver){
		ArrayList<CCSpriteFrame> frames=new ArrayList<CCSpriteFrame>();
		//String format="image/loading/loading_%02d.png";
		for(int i=1;i<=num;i++){
			CCSpriteFrame spriteFrame = CCSprite.sprite(String.format(format, i)).displayedFrame();
			frames.add(spriteFrame);
		}
		CCAnimation anim=CCAnimation.animation("", 0.2f, frames);
		// 序列帧一般必须永不停止的播放  不需要永不停止播放,需要指定第二个参数 false
		if(isForerver){
			CCAnimate animate=CCAnimate.action(anim);
			CCRepeatForever forever=CCRepeatForever.action(animate);
			return forever;
		}else{
			CCAnimate animate=CCAnimate.action(anim,false);
			return animate;
		}
		
	}
}
