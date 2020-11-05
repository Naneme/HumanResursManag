package com.example.humanresursmanag.recycle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.humanresursmanag.R;
import com.example.humanresursmanag.model.Employ;

public class EmployAdapter extends ListAdapter<Employ, EmployAdapter.EmployHolder> {

    private OnItemClickListener listener;

    public EmployAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Employ> DIFF_CALLBACK = new DiffUtil.ItemCallback<Employ>() {
        @Override
        public boolean areItemsTheSame(@NonNull Employ oldItem, @NonNull Employ newItem) {

            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Employ oldItem, @NonNull Employ newItem) {
            return oldItem.getName().equals(newItem.getName()) &&
                    oldItem.getFirsName().equals(newItem.getFirsName()) &&
                    oldItem.getLastName().equals(newItem.getLastName());
        }
    };

    @NonNull
    @Override
    public EmployHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_employ, parent, false);
        return new EmployHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployHolder holder, int position) {
        Employ currentEmploy = getItem(position);
        holder.textViewName.setText(currentEmploy.getName());
        holder.textViewFirstName.setText(currentEmploy.getFirsName());
        holder.textViewLastName.setText(currentEmploy.getLastName());
    }

    public Employ getEmployAt(int position) {
        return getItem(position);
    }

    class EmployHolder extends RecyclerView.ViewHolder {
        private TextView textViewName;
        private TextView textViewLastName;
        private TextView textViewFirstName;

        public EmployHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.text_view_name);
            textViewFirstName = itemView.findViewById(R.id.text_view_firstName);
            textViewLastName = itemView.findViewById(R.id.text_view_lastName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }

                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Employ employ);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;

    }

}
