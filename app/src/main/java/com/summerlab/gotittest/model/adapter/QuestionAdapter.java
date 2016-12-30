package com.summerlab.gotittest.model.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.summerlab.gotittest.R;
import com.summerlab.gotittest.model.QuestionResponse;
import com.summerlab.gotittest.utils.Utilities;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by trieulh on 12/30/16.
 */
public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.MyViewHolder> {

    private final Context mContext;
    private List<QuestionResponse> questionResponseList;

    public QuestionAdapter(Context context, List<QuestionResponse> list) {
        this.mContext = context;
        this.questionResponseList = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.question_list_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        QuestionResponse questionResponse = questionResponseList.get(position);
        //TODO bind Data

        holder.tvTitle.setText(questionResponse.getTitle());
        holder.tvTag.setText(questionResponse.getProcessing_status());

        long time = questionResponse.getUpdated();
        holder.tvTime.setText(Utilities.getTimeAgo(time));

        ImageLoader.getInstance().displayImage(questionResponse.getAuthor().getAvatar(), holder.imgAvatar, Utilities.getDisplayOptions());
        ImageLoader.getInstance().displayImage(questionResponse.getAttachments().get(0).getUrl(), holder.imgAttachment, Utilities.getDisplayOptions());
    }

    @Override
    public int getItemCount() {
        return questionResponseList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView imgAttachment;
        public CircleImageView imgAvatar;
        public TextView tvTitle, tvTime, tvTag;

        public MyViewHolder(View view) {
            super(view);
            imgAttachment = (ImageView) view.findViewById(R.id.img_attachment);
            imgAvatar = (CircleImageView) view.findViewById(R.id.img_ava);

            tvTime = (TextView) view.findViewById(R.id.tv_time);
            tvTitle = (TextView) view.findViewById(R.id.tv_title);
            tvTag = (TextView) view.findViewById(R.id.tv_tag);

        }
    }

}
