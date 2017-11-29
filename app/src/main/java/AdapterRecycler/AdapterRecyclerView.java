package AdapterRecycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anhkhoa.testbuttonlike.Comment;
import com.example.anhkhoa.testbuttonlike.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.like.LikeButton;
import com.like.OnLikeListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anh khoa on 11/18/2017.
 */

public class AdapterRecyclerView extends RecyclerView.Adapter<AdapterRecyclerView.ViewHolder> {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myLike = database.getReference("Likeuser");
    DatabaseReference LikeCount = database.getReference("LikeCount");
    DatabaseReference count = database.getReference("Comment");

    ArrayList<Comment> listComment;
    Context context;
    String iduserlike="";
    int dem=0;
    int counted;
    boolean check = false;

    public AdapterRecyclerView(ArrayList<Comment> listComment, Context context, String iduserlike) {
        this.listComment = listComment;
        this.context = context;
        this.iduserlike = iduserlike;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View row=layoutInflater.inflate(R.layout.itemlist,null,false);

        return new ViewHolder(row);
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.txtnoidung.setText(listComment.get(position).getCmtContent());
        holder.txtten.setText(listComment.get(position).getIduser());
        holder.txtcount.setText(listComment.get(position).getCount()+"");

        holder.likeButton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                boolean check = true;
                likeButton.setLiked(true);
                myLike.child(iduserlike).child(listComment.get(position).getIdcomment()).child("idlike").setValue(1);
                getLikeCount(position,listComment,count,holder,check);
        }

            @Override
            public void unLiked(LikeButton likeButton) {
                boolean check = false;

                myLike.child(iduserlike).child(listComment.get(position).getIdcomment()).removeValue();
                getLikeCount(position,listComment,count,holder,check);
            }
        });

        myLike.child(iduserlike).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if(listComment.get(position).getIdcomment().equals(dataSnapshot.getKey())){
                    check = true;
                    holder.txtten.setText(listComment.get(position).getIduser());
                    holder.txtnoidung.setText(listComment.get(position).getCmtContent());
                    if(check==true){
                        holder.likeButton.setLiked(true);
                    }else{

                        holder.likeButton.setLiked(false);
                    }
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return listComment.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtten,txtnoidung,txtcount;
        LikeButton likeButton;
        public ViewHolder(View itemView) {
            super(itemView);
            txtcount=itemView.findViewById(R.id.txtcount);
    likeButton=itemView.findViewById(R.id.thumb_button);
    txtten=itemView.findViewById(R.id.txttenuser);
    txtnoidung=itemView.findViewById(R.id.txtnoidung);
}
    }
    public void clear() {
        int size = this.listComment.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                this.listComment.remove(0);
            }

            this.notifyItemRangeRemoved(0, size);
        }
    }
    public void getLikeCount(final int position, final ArrayList<Comment> listComment, final DatabaseReference count, final ViewHolder holder, final boolean check){
        final boolean s[]={false};
        count.child(listComment.get(position).getIdcomment()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(s[0] == false){
                    Comment comment = dataSnapshot.getValue(Comment.class);
                    counted = comment.getCount();
                    Log.i("count",counted+"");
                    if(check == true){
                        count.child(listComment.get(position).getIdcomment()).child("count").setValue(counted+1);
                        Log.i("count",counted+"");
                        holder.txtcount.setText(String.valueOf(counted+1));
                    }
                    else{
                        count.child(listComment.get(position).getIdcomment()).child("count").setValue(counted-1);
                        Log.i("count",counted+"");
                        holder.txtcount.setText(String.valueOf(counted-1));
                    }
                    s[0] = true;
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
