package br.com.coalman.opennebulamobilemanager.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListPopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

import br.com.coalman.opennebulamobilemanager.R;
import br.com.coalman.opennebulamobilemanager.model.MaquinaVirtual;

/**
 * Created by Daniel on 16/12/2015.
 */
public class MVAdapter extends RecyclerView.Adapter<MVAdapter.MvViewHolder> {
    private List<MaquinaVirtual> mvs;

    public MVAdapter(List<MaquinaVirtual> mvs){
        this.mvs = mvs;
    }


    @Override
    public void onBindViewHolder(MvViewHolder holder, int position) {
        MaquinaVirtual mv = mvs.get(position);
        holder.nomeMV.setText(mv.getNome());
        holder.statusMV.setText(mv.getStatus());
        holder.imagemMV.setImageResource(R.drawable.mv);

    }

    @Override
    public int getItemCount() {
        return mvs.size();
    }

    @Override
    public MvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview, parent, false);
        MvViewHolder viewHolder = new MvViewHolder(view);
        return viewHolder;
    }

    public static class MvViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView nomeMV;
        TextView statusMV;
        ImageView imagemMV;

        MvViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cardView);
            nomeMV = (TextView) itemView.findViewById(R.id.textViewNome);
            statusMV = (TextView) itemView.findViewById(R.id.textViewStatus);
            imagemMV = (ImageView) itemView.findViewById(R.id.imagemMV);
        }

    }

}
