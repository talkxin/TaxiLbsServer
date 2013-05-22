package com.taxi.admin.common;

import java.util.ArrayList;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2011-5-25 上午10:19:11
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: guodong@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
public class DTree {

	/**
	 * 父节点
	 */
	private DTree parent;

	/**
	 * 打开状态Icon
	 */
	private String openIcon;

	/**
	 * 关闭状态Icon
	 */
	private String closeIcon;

	/**
	 * 显示的节点名称
	 */
	private String title;

	/**
	 * 链接
	 */
	private String link;

	/**
	 * id唯一标识
	 */
	private String oid;
	
	private String target;
	
	private boolean check;
	
	private boolean ischeck;
	
	private String html;
	
	private String frame;

	/**
	 * 子节点
	 */
	private ArrayList<DTree> childs;

	public DTree(DTree parent, String openIcon, String closeIcon, String title, String link, String oid,String target,String frame,boolean check,boolean ischeck,String html) {
		super();
		this.parent = parent;
		this.openIcon = openIcon;
		this.closeIcon = closeIcon;
		this.title = title;
		this.link = link;
		this.oid = oid;
		this.target=target;
		this.frame=frame;
		this.check=check;
		this.ischeck=ischeck;
		this.html=html;
		this.childs = new ArrayList<DTree>();
	}

	/**
	 * 创建根节点
	 * 
	 * @param _icon
	 * @param _icon2
	 * @param _title
	 * @param _link
	 * @param _oid
	 * @return
	 */
	public static DTree createRoot(String _icon, String _icon2, String _title, String _link, String _oid,String _target,String _frame,boolean _check,boolean _ischeck,String _html) {
		DTree dTree = new DTree(null, _icon, _icon2, _title, _link, _oid,_target,_frame,_check,_ischeck, _html);
		return dTree;
	}

	/**
	 * 初始化整个树返回符合dtreejs定义的默认样式树结构
	 * 
	 * @param dTree
	 * @return
	 */
	public static String initDefaultTree(DTree dTree) {
		StringBuffer sb = new StringBuffer();
		sb.append("d.add(");
		sb.append(dTree.getOid());
		if (dTree.getParent() == null) {
			sb.append(",-1,'");
		} else {
			sb.append(",").append(dTree.getParent().getOid()).append(",'");
		}
		sb.append(dTree.getTitle());
		sb.append("','");
		sb.append(dTree.getLink());
		sb.append("','");
		sb.append(dTree.getTarget());
		sb.append("','");
		sb.append(dTree.getFrame());
		sb.append("',");
		sb.append(dTree.isCheck());
		sb.append(",");
		sb.append(dTree.isIscheck());
		sb.append(",'");
		sb.append(dTree.getHtml());
		sb.append("');\r\n");
		appendChild(dTree, sb);
		return sb.toString();
	}

	/**
	 * 由根节点递归追加所有的子节点
	 * 
	 * @param parent
	 * @param sb
	 * @return
	 */
	private static StringBuffer appendChild(DTree parent, StringBuffer sb) {
		for (DTree child : parent.getChilds()) {
			sb.append("d.add(");
			sb.append(child.getOid());
			sb.append(",");
			sb.append(parent.getOid());
			sb.append(",'");
			sb.append(child.getTitle());
			sb.append("','");
			sb.append(child.getLink());
			sb.append("','");
			sb.append(child.getTarget());
			sb.append("','");
			sb.append(child.getFrame());
			sb.append("',");
			sb.append(child.isCheck());
			sb.append(",");
			sb.append(child.isIscheck());
			sb.append(",'");
			sb.append(child.getHtml());
			sb.append("');\r\n");
			appendChild(child, sb);
		}
		return sb;
	}

	/**
	 * 追加子树
	 * 
	 * @param _openIcon
	 * @param _closeIcon
	 * @param _title
	 * @param _link
	 * @param _oid
	 * @return
	 */
	public DTree appendChild(String _openIcon, String _closeIcon, String _title, String _link, String _oid,String _target,String _frame,boolean _check,boolean _ischeck,String _html) {
		DTree child = new DTree(this, _openIcon, _closeIcon, _title, _link, _oid,_target,_frame,_check,_ischeck,_html);
		childs.add(child);
		return child;
	}

	public DTree getParent() {
		return parent;
	}

	public void setParent(DTree parent) {
		this.parent = parent;
	}

	public String getOpenIcon() {
		return openIcon;
	}

	public void setOpenIcon(String openIcon) {
		this.openIcon = openIcon;
	}

	public String getCloseIcon() {
		return closeIcon;
	}

	public void setCloseIcon(String closeIcon) {
		this.closeIcon = closeIcon;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}
	
	
    public String getHtml() {
    	return html;
    }

    
    public String getTarget() {
    	return target;
    }

	
    public void setTarget(String target) {
    	this.target = target;
    }

	
    public boolean isCheck() {
    	return check;
    }

	
    public void setCheck(boolean check) {
    	this.check = check;
    }

	
    public boolean isIscheck() {
    	return ischeck;
    }

	
    public void setIscheck(boolean ischeck) {
    	this.ischeck = ischeck;
    }

	public void setHtml(String html) {
    	this.html = html;
    }

	public ArrayList<DTree> getChilds() {
		return childs;
	}

	public void setChilds(ArrayList<DTree> childs) {
		this.childs = childs;
	}
	

	
    public String getFrame() {
    	return frame;
    }

	
    public void setFrame(String frame) {
    	this.frame = frame;
    }

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

}