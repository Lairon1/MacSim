package com.lairon.macsim.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lairon.macsim.R;
import com.lairon.macsim.obj.Client;
import com.lairon.macsim.obj.Tariff;
import com.lairon.macsim.servlet.api.MacSimWepApi;
import com.lairon.macsim.utils.ActivityUtils;
import com.lairon.macsim.utils.Globals;

import java.util.List;

public class TariffAdapter extends RecyclerView.Adapter<TariffAdapter.TariffHolder> {

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

    public class TariffHolder extends RecyclerView.ViewHolder {

        private TextView tariffNameText, gigabytesText, smsText, minutesText, descriptionText, priceText;
        private Button connectButton;
        private Tariff tariff;

        public TariffHolder(@NonNull View view) {
            super(view);
            tariffNameText = view.findViewById(R.id.TariffNameText);
            smsText = view.findViewById(R.id.SMSText);
            gigabytesText = view.findViewById(R.id.GigabytesText);
            minutesText = view.findViewById(R.id.MinutesText);
            descriptionText = view.findViewById(R.id.DescriptionText);
            priceText = view.findViewById(R.id.PriceText);
            connectButton = view.findViewById(R.id.ConnectButton);
            connectButton.setOnClickListener(this::connectButtonClick);
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

        @SuppressLint("StaticFieldLeak")
        public void connectButtonClick(View view) {

            new AlertDialog.Builder(view.getContext())
                    .setTitle("Вы действительно хотите преобрести тарифф \"" + tariff.getName() + "\"")
                    .setNegativeButton("Отмена", (dialog, which) -> { })
                    .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Dialog loadingDialog = ActivityUtils.startLoadingDialog(view.getContext());

                            MacSimWepApi wepApi = new MacSimWepApi();
                            new AsyncTask<Void, Void, Client>() {

                                private Exception exception;

                                @Override
                                protected Client doInBackground(Void... voids) {
                                    try {
                                        return wepApi.hookUpTariff(Globals.getCurrentClient(), tariff);
                                    } catch (Exception e) {
                                        exception = e;
                                    }
                                    return null;
                                }

                                @Override
                                protected void onPostExecute(Client client) {
                                    if (client == null) {
                                        loadingDialog.hide();
                                        ActivityUtils.sendError(exception.getMessage(), view.getContext());
                                        return;
                                    }
                                    Globals.setCurrentClient(client);
                                    loadingDialog.hide();
                                    ActivityUtils.sendInfo("Тариф успешно подключен!", view.getContext());

                                    super.onPostExecute(client);
                                }
                            }.execute();
                        }
                    }).create().show();
        }
    }
}
