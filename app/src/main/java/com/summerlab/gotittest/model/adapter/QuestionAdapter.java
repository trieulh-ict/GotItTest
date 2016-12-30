package com.summerlab.gotittest.model.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.summerlab.gotittest.R;
import com.summerlab.gotittest.model.Question;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by trieulh on 12/30/16.
 */
public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.MyViewHolder> {

    private ArrayList<Question> questionList;

    public QuestionAdapter(ArrayList<Question> list) {
        this.questionList = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.question_list_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Question question = questionList.get(position);
        //TODO bind Data




    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView imgAttachment;
        public CircleImageView imgAvatar;
        public TextView tvTitle, tvTime;

        public MyViewHolder(View view) {
            super(view);
            imgAttachment = (ImageView) view.findViewById(R.id.img_attachment);
            imgAvatar = (CircleImageView) view.findViewById(R.id.img_ava);

            tvTime = (TextView) view.findViewById(R.id.tv_time);
            tvTitle = (TextView) view.findViewById(R.id.tv_title);

        }
    }

}
