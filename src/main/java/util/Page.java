package util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Page<T> {
    private int index = 1; //(当前页数-1)*数量
    private int pageSize = 1;  //每页数量 v
    private int pages=1; //总页数 total-1/pageSize +1 v
    private int total=1; //总记录数 v
    private int nowPageNumber=1; //当前页数
    private int lastNumber=1; //集合尾页
    private int[] pageNumber; //页数集合 v
    private List<T> list; //结果集 v

    public void setPageNumber(int params){
        pageNumber= new int[3]; //(total-1)/pageSize +1
        this.lastNumber=pages;
        if(params>1&&params< pages) {
            int x = 0;
            for (int i = params - 1; i <= params+1; i++) {
                pageNumber[x] = i;
                x++;
            }
        }else if(params==1){
            for (int i = 0; i < params+2; i++) {
                pageNumber[i] = i+1;
            }
        }else if(params==pages){
            int x = 0;
            for (int i = params-2; i <= pages; i++) {
                pageNumber[x] = i;
                x++;
            }
        }else if(params==-2){
            pageNumber= new int[1];
            pageNumber[0]=1;
            this.lastNumber = pageNumber[pageNumber.length-1];
        }else if(params==-1){
            pageNumber= new int[2];
            pageNumber[0]=1;
            pageNumber[1]=2;
            this.lastNumber = pageNumber[pageNumber.length-1];
        }
    }

    public int count(String tableName) throws SQLException {
        StringBuffer sb = new StringBuffer("select count(*) as ct from ");
        sb.append(tableName+" where 1 = 1 ");
        String sql = sb.toString();
        ResultSet re = DBUtil.executeQuery(sql,null);
        while (re.next()){
            this.total = re.getInt("ct");
        }
        return total;
    }

    public void setParams(int count,int pNumber,int size){
        setTotal(count);
        setPageSize(size);
        setPages((count-1)/getPageSize()+1);
        setPageNumber(pNumber);
        setNowPageNumber(pNumber);
        setIndex((pNumber-1)*getPageSize());
    }

    public ResultSet paging(int index,int pageSize,String tableName) throws SQLException {
        StringBuffer sb = new StringBuffer("select * from ");
        sb.append(tableName+" limit "+index+" , "+pageSize);
        ResultSet re = DBUtil.executeQuery(sb.toString());
        return re;
    }

    public ResultSet pagingByLike(int index,int pageSize,String tableName,String key) throws SQLException {
        StringBuffer sb = new StringBuffer("select * from ");
        sb.append(tableName);
        sb.append(" where title like '%"+key+"%'");
        sb.append(" limit "+index+" , "+pageSize);
        ResultSet re = DBUtil.executeQuery(sb.toString());
        return re;
    }
    public void setNowPageNumber(int nowPageNumber) {
        this.nowPageNumber = nowPageNumber;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getIndex() {
        return index;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getTotal() {
        return total;
    }

    public int getPages(){
        return pages;
    }

    public int[] getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int[] pageNumber) {
        this.pageNumber = pageNumber;
    }

    public List<T> getList() {
        return list;
    }

    public int getNowPageNumber() {
        return nowPageNumber;
    }

    public int getLastNumber() {
        return lastNumber;
    }

    public void setLastNumber(int lastNumber) {
        this.lastNumber = lastNumber;
    }
}
