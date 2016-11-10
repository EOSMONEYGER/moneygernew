package com.example.user.moneyger2.dbsql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.user.moneyger2.data.RankingData;
import com.example.user.moneyger2.data.SearchData;

import java.util.ArrayList;

/**
 * Created by User on 2016-11-10.
 */
public class DBManager {

    private final static String TABLE_NAME = "debtlist";
    private Context context;

    private MySQLOpenHelper helper;
    private SQLiteDatabase db;

    public DBManager(Context context) {
        this.context = context;

        /** DB 관련 변수 셋팅.
         * 호출하는 곳에 따라 parameters 를 다르게 줘야 한다면
         * 해당 변수들을 이 클래스가 아닌 호출하는 위치에서 셋팅한 후,
         * 이 클래스의 멤버변수에 대입하는 방식으로 구현해야 합니다. **/
        helper = new MySQLOpenHelper(context);
        db = helper.getWritableDatabase(); // 읽고 쓸 수 있는 Database 셋팅
    }

    public ArrayList<RankingData> getRanking() {
        ArrayList<RankingData> ranking = new ArrayList<>();

        Cursor c = db.rawQuery("SELECT SUM(debt) AS price, name FROM debtlist GROUP BY phonenum ORDER BY price DESC;", new String[] {});

        int i = 1;

        while (c.moveToNext()) {
            if(c.getInt(c.getColumnIndex("price")) == 0) break;
            ranking.add(new RankingData(i++ + "위", c.getString(c.getColumnIndex("name")), c.getInt(c.getColumnIndex("price")) + "원"));
        }
        c.close();

        return ranking;
    }
    public ArrayList<SearchData> getSearch() {
        ArrayList<SearchData> search = new ArrayList<>();

        Cursor c = db.rawQuery("SELECT SUM(debt) AS price, name, phonenum FROM debtlist GROUP BY phonenum ORDER BY name;", new String[] {});

        while (c.moveToNext()) {
            if(c.getInt(c.getColumnIndex("price")) == 0) continue;
            search.add(new SearchData(false, c.getString(c.getColumnIndex("name")),c.getString(c.getColumnIndex("phonenum")), c.getInt(c.getColumnIndex("price")) + "원"));
        }
        c.close();

        return search;
    }

    public void update(SearchData contact, int minus_debt) {

        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);//선택 조건 없는 쿼리 실행(=DB 테이블의 레코드 전체를가져옴)

        while (c.moveToNext()) {
            if(contact.getPh_num().equals(c.getString(c.getColumnIndex("phonenum"))) ){
                if(c.getInt(c.getColumnIndex("debt")) <= minus_debt) {
                    ContentValues values = new ContentValues();

                    minus_debt -= c.getInt(c.getColumnIndex("debt"));

                    values.put("name", contact.getName());
                    values.put("phonenum", contact.getPh_num());
                    values.put("debt", 0);
                    db.update(TABLE_NAME, values, "cid=" + c.getInt(c.getColumnIndex("cid")), null);
                } else {
                    int debt = c.getInt(c.getColumnIndex("debt")) - minus_debt;
                    ContentValues values = new ContentValues();

                    values.put("name", contact.getName());
                    values.put("phonenum", contact.getPh_num());
                    values.put("debt", debt);
                    db.update(TABLE_NAME, values, "cid=" + c.getInt(c.getColumnIndex("cid")), null);
                }
            }
        }

        // contact table 의 data 중 cid 값이 입력받은 contact data 의 id 값과 일치하는 data 의 값을 수정합니다.

        Toast.makeText(context, "수정되었습니다.", Toast.LENGTH_SHORT).show();
    }

    /**
     * Table 의 특정 data 를 삭제합니다.
     *
     * @param id 수정할 data 의 id
     */
    public void delete(int id) {
        // contact table 의 data 중 cid 값이 입력받은 id 값과 일치하는 data 를 삭제합니다.
        db.delete("contact", "cid=?", new String[]{id + ""});

        Toast.makeText(context, "삭제되었습니다.", Toast.LENGTH_SHORT).show();
    }

}