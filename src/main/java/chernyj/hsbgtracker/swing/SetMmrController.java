package chernyj.hsbgtracker.swing;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import chernyj.hsbgtracker.utils.observers.SetMmrObserver;
import chernyj.hsbgtracker.utils.subjects.SetMmrSubject;

public class SetMmrController implements SetMmrSubject {
	
	private List<SetMmrObserver> observers = new ArrayList<>();
	
	private SetMmrDialog dialog;
	
	public SetMmrController(SetMmrDialog dialog) {
		this.dialog = dialog;
		
		setListeners();
	}
	
	private void setListeners() {
		dialog.getBtnOk().addActionListener(l->doOkAction());
	}

	private void doOkAction() {
		String mmr = dialog.getTfMMR().getText();
		int mmrInt = 0;
		try {
			mmrInt = Integer.parseInt(mmr);
		} catch(NumberFormatException e) {
			
			dialog.getTfMMR().setBackground(Color.RED);
			return;
		}
		
		if(mmrInt <= 0 || mmrInt > 20000) {
			dialog.getTfMMR().setBackground(Color.RED); 
			return;
		}
		
		dialog.getTfMMR().setBackground(Color.WHITE);
		
		notifyObservers(mmrInt);
		
		dialog.dispose();
		
	}

	@Override
	public void register(SetMmrObserver observer) {
		observers.add(observer);
		
	}

	@Override
	public void remove(SetMmrObserver observer) {
		observers.remove(observer);
	}

	@Override
	public void notifyObservers(int mmr) {
		observers.forEach(o->o.update(mmr));
		
	}

}
