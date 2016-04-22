package sgd13.com.appproyectogranjasgd.entradas.fragments;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import java.util.Calendar;

import sgd13.com.appproyectogranjasgd.R;

public class AguaFragment extends Fragment {
	Spinner spinner;
	EditText txtDate;
	Button boton;
	Switch switchFecha;

	DatePickerDialog dpd;
	AlertDialog alert;
	private int mYear, mMonth, mDay;
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.fragment_agua_layout, container, false);

		txtDate = (EditText) rootView.findViewById(R.id.txtdate);
		switchFecha = (Switch) rootView.findViewById(R.id.switch1);
		boton = (Button) rootView.findViewById(R.id.btnShowDialog);
		boton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View rootView) {
				alert.show();
			}
		});
		mostrarFechaActual();

		switchFecha.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (switchFecha.isChecked())
					dpd.show();
				else
					mostrarFechaActual();
			}
		});
		dpd = new DatePickerDialog(getContext(),
				new DatePickerDialog.OnDateSetListener() {

					@Override
					public void onDateSet(DatePicker view, int year,
										  int monthOfYear, int dayOfMonth) {
						txtDate.setText(dayOfMonth + "-"
								+ (monthOfYear + 1) + "-" + year);

					}
				}, mYear, mMonth, mDay);

		AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

		builder.setTitle("Rellenar");
		builder.setMessage("¿Estás seguro?");

		builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				//Snackbar.make(getContext(), "Se ha recargado el deposito de agua", Snackbar.LENGTH_LONG,).show();
				Snackbar.make(getView(), "Se ha rellendo el deposito de agua", Snackbar.LENGTH_LONG).show();
				dialog.dismiss();
			}

		});

		builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});

		alert = builder.create();


		return rootView;
	}

	public void mostrarFechaActual() {
		final Calendar c = Calendar.getInstance();
		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH) + 1;
		mDay = c.get(Calendar.DAY_OF_MONTH);
		txtDate.setText("" + mDay + "-" + mMonth + "-" + mYear);
	}
}
