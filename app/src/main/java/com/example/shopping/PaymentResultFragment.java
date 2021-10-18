package com.example.shopping;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;

import static com.example.shopping.SecondCartFragment.ORDER_KEY;

public class PaymentResultFragment extends Fragment {

    private TextView txtMessage;
    private Button btnHome;

    @Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payment_result, container, false);

        initViews(view);

        Bundle bundle = getArguments();
        if (null != bundle){
            String jsonOrder = bundle.getString(ORDER_KEY);
            if (null != jsonOrder){
                Gson gson = new Gson();
                Type type = new TypeToken<Order>(){}.getType();
                Order order = gson.fromJson(jsonOrder,type);
                if (null != order){
                    if (order.isSuccess()) {
                        txtMessage.setText("Payment was successful\nYour Order will arrive in 3 days");

                        // Clear cart Items in shared preferences, not needed the items inside the cart
                        Utils.clearCartItems(getActivity());

                        //Increase the popularity point
                        for (GroceryItem item: order.getItems()) {
                            Utils.increasePopularityPoint(getActivity(), item, 1);
                            Utils.changeUserPoint(getActivity(), item, 4); // +4 user poeni za site kupeni proizvodi
                        }

                        //Increase also the user point


                    } else  {
                        txtMessage.setText("Payment failed,\nPlease try another payment method");
                    }
                }
            } else {
                txtMessage.setText("Payment failed,\nPlease try another payment method");
            }
        }

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        return view;
    }

    private void initViews(View view) {
        txtMessage = view.findViewById(R.id.txtMessage);
        btnHome = view.findViewById(R.id.btnHome);
    }
}

