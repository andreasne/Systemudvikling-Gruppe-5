package com.example.databasetest;

import java.util.List;
import sqlite.model.Ingredient;
import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class IngredientAdapter extends ArrayAdapter<Ingredient> {

	private List<Ingredient> items;
	private int layoutResourceId;
	private Context context;
	
	
	public IngredientAdapter(Context context, int layoutResourceId, List<Ingredient> items) {
		super(context, layoutResourceId, items);
		this.context = context;
		this.layoutResourceId = layoutResourceId;
		this.items = items;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		AtomPaymentHolder holder = null;

		LayoutInflater inflater = ((Activity) context).getLayoutInflater();
		row = inflater.inflate(layoutResourceId, parent, false);

		
		
		holder = new AtomPaymentHolder();
		holder.ingredient = items.get(position);
		holder.name = (TextView)row.findViewById(R.id.atomPay_name);
		
		row.setTag(holder);

		setupItem(holder);
		return row;
	}
		
	private void setupItem(AtomPaymentHolder holder) {
		holder.name.setText(holder.ingredient.getIngredient());
		//holder.value.setText(String.valueOf(holder.atomPayment.getValue()));
	}

	public static class AtomPaymentHolder {
		Ingredient ingredient;
		TextView name;
		//TextView value;
		//ImageButton removePaymentButton;
	}	
	
	private void setNameTextChangeListener(final AtomPaymentHolder holder) {
		holder.name.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				holder.ingredient.setIngredient(s.toString());
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

			@Override
			public void afterTextChanged(Editable s) { }
		});
	}
}

