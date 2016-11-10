package com.example.user.moneyger2.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.user.moneyger2.R;
import com.example.user.moneyger2.SearchCheckActivity;
import com.example.user.moneyger2.data.SearchData;

import java.util.ArrayList;

/**
 * Created by User on 2016-11-10.
 */
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder>  {
    private ArrayList<SearchData> searchList = new ArrayList<>(); // RecyclerView에 표시할 data list
    private Context context;

    /**
     *
     * @param context
     * @param searchList : data list 를 받아와서 셋팅
     */
    public SearchAdapter(Context context, ArrayList<SearchData> searchList) {
        this.context = context;
        this.searchList = searchList;
    }

    /**
     * ViewHolder 를 생성해서 반환하는 역할
     *
     * @param parent
     * @param viewType
     * @return 생성한 ViewHolder를 반환
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 각각의 item 을 표현하는 layout (intent_item.xml) 파일을 가져와서 View 로 만들어 준다! (LayoutInflater 이용)
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_fragment_search, parent, false);
        // 방금 만든 view (intent_item.xml 로 만든 거 ㅇㅇ) 를 제물로 바쳐 ViewHolder를 소환한다
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    /**
     * 각각의 item view에 데이터를 셋팅함
     *
     * @param holder 셋팅할 view 들을 잡고 있는 ViewHolder 인스턴스
     * @param position 현재 그릴 item의 position (보통 현재 그릴 data 의 list index 와 같은 값을 가집니다)
     */
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        // 그려줄 IntentData 를 셋팅
        final SearchData search = searchList.get(position);

        if (search != null) { // Error Handling

            holder.chk.setChecked(search.isCheck_state());
            holder.name.setText(search.getName());
            holder.ph_num.setText(search.getPh_num());
            holder.debt.setText(search.getDebt());

            holder.chk.setOnClickListener(new View.OnClickListener() {//체크박스의 클릭리스너.
                @Override
                public void onClick(View view) {
                    CheckBox v = (CheckBox) view;
                    if (v.isChecked())//체크박스가 체크된 상태일 때.
                        search.setCheck_state(true);//데이터 클래스 객체의 check_state값을 true로 초기화.
                    else//언체크드일 때
                        search.setCheck_state(false);//false로 초기화.
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(final View view) {
                    //new DBManager(view.getContext()).update(new SearchData(false,search.getName(),search.getPh_num(),search.getDebt()));

                    Intent intent = new Intent(context,SearchCheckActivity.class);
                    context.startActivity(intent);

                    return false;
                }
            });
        }
    }

    /**
     * @return data list 의 item 갯수를 반환
     */
    @Override
    public int getItemCount() {
        return searchList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox chk; // 내 item view 의 ImageView를 담을 인스턴스
        TextView name,debt,ph_num;// 내 item view 의 TextView를 담을 인스턴스

        public ViewHolder(View itemView) {
            super(itemView);

            chk = (CheckBox)itemView.findViewById(R.id.search_list_check);
            name = (TextView)itemView.findViewById(R.id.search_list_name);
            ph_num = (TextView)itemView.findViewById(R.id.search_list_phonenum);
            debt = (TextView)itemView.findViewById(R.id.search_list_debt);
        }
    }
}
