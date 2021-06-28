package com.lkkw.projetocrudsqlite.view;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.lkkw.projetocrudsqlite.R;
import com.lkkw.projetocrudsqlite.controller.ContatoController;
import com.lkkw.projetocrudsqlite.model.Contato;
import com.lkkw.projetocrudsqlite.view.MainActivity;

public class OnClickListener implements View.OnClickListener{
    @Override
    public void onClick(View v) {
        final Context context = v.getContext();

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.contato_form, null, false);

        final EditText editTextContatoNome = formElementsView.findViewById(R.id.editTextContatoNome);
        final EditText editTextContatoEmail = formElementsView.findViewById(R.id.editTextContatoEmail);

        new AlertDialog.Builder(context)
                .setView(formElementsView)
                .setTitle("Criar Contato")
                .setPositiveButton("Incluir", (dialog, id) -> {
                    String contatoNome = editTextContatoNome.getText().toString();
                    String contatoEmail = editTextContatoEmail.getText().toString();

                    Contato contato = new Contato();
                    contato.setNome(contatoNome);
                    contato.setEmail(contatoEmail);

                    boolean isCreated = new ContatoController(context).create(contato);

                    if(isCreated) {
                        Toast.makeText(context, "Contrato incluido com sucesso", Toast.LENGTH_LONG).show();
                        ((MainActivity) context).contadorDeRegistros();
                        ((MainActivity) context).atualizarListaDeContatos();
                    }else {
                        Toast.makeText(context, "Erro ao incluir contato", Toast.LENGTH_LONG).show();
                    }

                    dialog.cancel();
                }).show();
    }
}
