package com.lairon.macsim.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lairon.macsim.R;
import com.lairon.macsim.obj.Tariff;

import java.util.List;

public class TariffAdapter extends RecyclerView.Adapter<TariffAdapter.TariffHolder>{

    private final LayoutInflater inflater;
    private final List<Tariff> tariffs;

    public TariffAdapter(Context context, List<Tariff> tariffs) {
        this.inflater = LayoutInflater.from(context);
        this.tariffs = tariffs;
    }

    @NonNull
    @Override
    public TariffHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.tariff_item, parent, false);
        return new TariffHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TariffHolder holder, int position) {
        holder.setTariff(tariffs.get(position));
    }

    @Override
    public int getItemCount() {
        return tariffs.size();
    }

    public class TariffHolder extends RecyclerView.ViewHolder{

        private TextView tariffNameText, gigabytesText, smsText, minutesText, descriptionText, priceText;
        private Button connectButton;
        private Tariff tariff;


        public TariffHolder(@NonNull View itemView) {
            super(itemView);
            tariffNameText = itemView.findViewById(R.id.TariffNameText);
            smsText = itemView.findViewById(R.id.SMSText);
            gigabytesText = itemView.findViewById(R.id.GigabytesText);
            minutesText = itemView.findViewById(R.id.MinutesText);
            descriptionText = itemView.findViewById(R.id.DescriptionText);
            priceText = itemView.findViewById(R.id.PriceText);
            connectButton = itemView.findViewById(R.id.ConnectButton);
        }

        public void setTariff(Tariff tariff) {
            this.tariff = tariff;

            tariffNameText.setText(tariff.getName());
            gigabytesText.setText(tariff.getGigabytes() == -1 ? "Безлимит" : String.valueOf(tariff.getGigabytes()));
            smsText.setText(tariff.getSms() == -1 ? "Безлимит" : String.valueOf(tariff.getSms()));
            minutesText.setText(tariff.getMinutes() == -1 ? "Безлимит" : String.valueOf(tariff.getMinutes()));

            descriptionText.setText(tariff.getDescription());
            priceText.setText(String.valueOf(((int) tariff.getPrice())));
        }
    }
}
