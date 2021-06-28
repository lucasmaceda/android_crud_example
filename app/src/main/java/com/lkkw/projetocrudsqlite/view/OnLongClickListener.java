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

public class OnLongClickListener implements View.OnLongClickListener {
    Context context;
    String id;

    @Override
    public boolean onLongClick(View v) {
        context = v.getContext();
        id = v.getTag().toString();

        final CharSequence[] itens = {"Editar", "Deletar"};

        new AlertDialog.Builder(context).setTitle("Detalhes do contato")
                .setItems(itens, (dialog, item) -> {
                    if(item == 0) {
                        editContatoPeloId(Integer.parseInt(id));
                    }else if(item == 1){
                      boolean isDeleted =
                              new ContatoController(context).delete(Integer.parseInt(id));

                      if(isDeleted){
                          Toast.makeText(context, "Contato deletado.",
                                  Toast.LENGTH_SHORT).show();
                          ((MainActivity) context).contadorDeRegistros();
                          ((MainActivity) context).atualizarListaDeContatos();
                      }else{
                          Toast.makeText(context, "Erro ao deletar o contato.",
                                  Toast.LENGTH_SHORT).show();
                      }
                    }

                    dialog.dismiss();
                }).show();

        return false;
    }

    public void editContatoPeloId(int contatoID){
        final ContatoController contatoController = new ContatoController(context);
        final Contato contato = contatoController.buscaPeloId(contatoID);

        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formContato
                = inflater.inflate(R.layout.contato_form, null, false);

        final EditText editTextNome = formContato.findViewById(R.id.editTextContatoNome);
        final EditText editTextEmail = formContato.findViewById(R.id.editTextContatoEmail);

        editTextNome.setText(contato.getNome());
        editTextEmail.setText(contato.getEmail());

        new AlertDialog.Builder(context)
                .setView(formContato)
                .setTitle("Editar")
                .setPositiveButton("Atualizar dados",
                    (dialog, id) -> {
                        Contato novoContato = new Contato();
                        novoContato.setId(contatoID);
                        novoContato.setNome(editTextNome.getText().toString());
                        novoContato.setEmail(editTextEmail.getText().toString());

                        boolean isUpdate = contatoController.update(novoContato);

                        if(isUpdate) {
                            Toast.makeText(context, "Contrato atualizado com sucesso.",
                                    Toast.LENGTH_LONG).show();
                            ((MainActivity) context).atualizarListaDeContatos();
                        }else {
                            Toast.makeText(context, "Erro ao atualizar contato.",
                                    Toast.LENGTH_LONG).show();
                        }

                        dialog.cancel();

                    }).show();


    }
}
