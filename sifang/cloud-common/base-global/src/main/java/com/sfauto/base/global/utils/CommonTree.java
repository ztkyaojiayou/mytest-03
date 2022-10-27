package com.sfauto.base.global.utils;



import com.google.common.base.Objects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CommonTree implements Comparable<CommonTree>{
    private String id;
    private String text;
    private String state;
    private List<CommonTree> children;
    private String iconCls;
    private boolean checked;
    private Integer sort;
    private String parent_id;
    private String display = "none";
    private int level;
    private String dtype;
    private String url;
    private String name;
    private String pid;
    private Object attributes;
    private boolean is_has_child = false;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDtype() {
        return dtype;
    }

    public void setDtype(String dtype) {
        this.dtype = dtype;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public CommonTree() {
        super();
    }

    public CommonTree(String id, String text) {
        super();
        this.id = id;
        this.text = text;
    }

    public CommonTree(String id, String text, String state) {
        super();
        this.id = id;
        this.text = text;
        this.state = state;
    }

    public CommonTree(String id, String text,boolean checked) {
        super();
        this.id = id;
        this.text = text;
        this.checked = checked;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<CommonTree> getChildren() {
        return children;
    }

    public void setChildren(List<CommonTree> children) {
        this.children = children;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Object getAttributes() {
        return attributes;
    }

    public void setAttributes(Object attributes) {
        this.attributes = attributes;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public boolean isIs_has_child() {
        return is_has_child;
    }

    public void setIs_has_child(boolean is_has_child) {
        this.is_has_child = is_has_child;
    }

    public static List<CommonTree> assembleTree(List<CommonTree> list){
        List<CommonTree> newList = list.stream().distinct().collect(Collectors.toList());   //去重

        //获取顶级节点
        List<CommonTree> parent = getRoot(newList);
        List<CommonTree> renewList=new ArrayList<CommonTree>();
        for(int j=0;j<parent.size();j++){
            CommonTree newtree=getChildrenTree(parent.get(j), newList);
            parent.get(j).setState("open");
            renewList.add(parent.get(j));
        }
        return renewList.isEmpty()?null:renewList;
    }

    public static List<CommonTree> getRoot(List<CommonTree> list){
        List<CommonTree> root=new ArrayList<CommonTree>();
        // 先找出所有的根节点
        for (int i = 0; i < list.size(); i++) {
            CommonTree node = list.get(i);
            Boolean isRoot = true;
            for (int j = 0; j < list.size(); j++) {
                CommonTree temp = list.get(j);
                if (node.getParent_id() != null && node.getParent_id().equalsIgnoreCase(temp.getId())) {
                    isRoot = false;
                    break;
                }
            }
            if (isRoot) {
                root.add(node);
            }
        }
        return root;
    }

    /**
     *
     * TODO: 递归实现查询子节点.
     * TODO: 填入方法说明
     * @param tree
     * @param list
     * @return
     */
    public static CommonTree getChildrenTree(CommonTree tree,List<CommonTree> list){
        if(list!=null&& !list.isEmpty()){
            List<CommonTree> treeList=new ArrayList<CommonTree>();
            for(int i=0;i<list.size();i++){
                if(tree.id.equals(list.get(i).getParent_id())){
                    treeList.add(list.get(i));
                    getChildrenTree(list.get(i), list);
                }
            }
            if(treeList.isEmpty()){
                return null;
            }else{
                Collections.sort(treeList);
                tree.setChildren(treeList);
                tree.setState("closed");
            }
        }else{
            return null;
        }
        return null;
    }

    @Override
    public int compareTo(CommonTree o) {
        // TODO Auto-generated method stub
        return this.getId().compareTo(o.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommonTree that = (CommonTree) o;
        return Objects.equal(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
