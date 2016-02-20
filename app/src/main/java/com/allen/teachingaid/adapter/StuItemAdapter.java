package com.allen.teachingaid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.allen.teachingaid.R;
import com.allen.teachingaid.util.ToastUtil;
import com.squareup.picasso.Picasso;

public class StuItemAdapter extends RecyclerView.Adapter<StuItemAdapter.ViewHolder> {

    private String[] mStu = {"林启南", "AllenLin", "林启南", "AllenLin", "林启南", "AllenLin", "林启南",
            "AllenLin", "林启南", "AllenLin", "林启南", "AllenLin", "林启南", "AllenLin"};
    Context mContext;

    public StuItemAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_course_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
//        holder.mItem = mValues.get(position);
//        holder.mIdView.setText(mValues.get(position).id);

        //Picasso.with(mContext).load("http://i.imgur.com/DvpvklR.png").into(holder.mImageView);
//        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_launcher);
//        holder.mImageView.setImageBitmap(bitmap);
        Picasso.with(mContext).setIndicatorsEnabled(true);
        Picasso.with(mContext)

                .load("http://i.imgur.com/DvpvklR.png")
                .resize(150, 150)
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.menu1)
                .into(holder.mImageView);
        holder.mContentView.setText(mStu[position]);


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showShort(position + mStu[position]);
//                Intent intent = new Intent(mContext, StuListActivity.class);
//                intent.putExtra("course_id", "123321");
//                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mStu == null ? 0 : mStu.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mImageView;
        public final TextView mContentView;


        public ViewHolder(View view) {
            super(view);
            mView = view;
//            mIdView = (TextView) view.findViewById(R.id.id);
            mImageView = (ImageView) view.findViewById(R.id.imageview);
            mContentView = (TextView) view.findViewById(R.id.contentview);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
