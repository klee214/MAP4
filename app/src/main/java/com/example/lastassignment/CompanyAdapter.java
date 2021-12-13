package com.example.lastassignment;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.TasksViewHolder> {

    interface companyClickListner {
        public void companyClicked(Company selectedCompany);
    }

    private Context mCtx;
    public List<Company> companyList;
    companyClickListner listner;

    public CompanyAdapter(Context mCtx, List<Company> companyList) {
        this.mCtx = mCtx;
        this.companyList = companyList;
        listner = (companyClickListner) mCtx;
    }

    @Override
    public TasksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recycle_companies, parent, false);
        return new TasksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TasksViewHolder holder, int position) {
        Company t = companyList.get(position);

        Log.d("position", Integer.toString(position));
        holder.companyTextView.setText(t.getCpSymbol() + " : " + t.getCpName());
    }

    @Override
    public int getItemCount() {
        return companyList.size();
    }

    class TasksViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView companyTextView;

        public TasksViewHolder(View itemView) {
            super(itemView);

            companyTextView = itemView.findViewById(R.id.company);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Company company = companyList.get(getAdapterPosition());
            listner.companyClicked(company);

        }
    }


}
