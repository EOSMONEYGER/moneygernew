package com.example.user.moneyger2.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.moneyger2.InfoActivity;
import com.example.user.moneyger2.R;
import com.example.user.moneyger2.data.InfoData;

import java.util.ArrayList;

/**
 * Created by User on 2016-11-07.
 */
public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.ViewHolder>{
    private ArrayList<InfoData> infoList = new ArrayList<>(); // RecyclerView에 표시할 data list
    private Context context;

    /**
     *
     * @param context
     * @param infoList : data list 를 받아와서 셋팅
     */
    public InfoAdapter(Context context, ArrayList<InfoData> infoList) {
        this.context = context;
        this.infoList = infoList;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_fragment_info, parent, false);
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
    public void onBindViewHolder(ViewHolder holder, int position) {
        // 그려줄 IntentData 를 셋팅
        final InfoData info = infoList.get(position);

        if (info != null) { // Error Handling

            holder.date.setText(info.getDate());
            holder.gathering.setText(info.getGathering());
            holder.image.setImageDrawable(context.getResources().getDrawable(info.getImageId()));

            // click event 처리
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, InfoActivity.class);
                    intent.putExtra("location", info.getGathering());
                    context.startActivity(intent);
                }
            });
        }
    }

    /**
     * @return data list 의 item 갯수를 반환
     */
    @Override
    public int getItemCount() {
        return infoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image; // 내 item view 의 ImageView를 담을 인스턴스
        TextView date,gathering;// 내 item view 의 TextView를 담을 인스턴스

        public ViewHolder(View itemView) {
            super(itemView);

            image = (ImageView)itemView.findViewById(R.id.info_list_imgbtn);
            date = (TextView)itemView.findViewById(R.id.info_list_date);
            gathering = (TextView)itemView.findViewById(R.id.info_list_gathering);
        }
    }
}
