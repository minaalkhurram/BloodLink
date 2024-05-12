import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<Users> userList;
    private Context context;

    public UserAdapter(Context context, List<Users> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_item_row, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        Users currentUser = userList.get(position);
        holder.usernameTextView.setText(currentUser.getUser());
        holder.passwordTextView.setText(currentUser.getPassword());
        holder.medicalDiseaseTextView.setText(currentUser.getmedicalDisease() ? "Yes" : "No");

        // Add onClickListener for callButton and chatButton if needed
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        TextView usernameTextView, passwordTextView, medicalDiseaseTextView;
        ImageButton callButton, chatButton;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            usernameTextView = itemView.findViewById(R.id.usernametxt);
            passwordTextView = itemView.findViewById(R.id.passtxt);
            medicalDiseaseTextView = itemView.findViewById(R.id.medDiseasetxt);
            callButton = itemView.findViewById(R.id.callbtn);
            chatButton = itemView.findViewById(R.id.chatbtn);
        }
    }
}

