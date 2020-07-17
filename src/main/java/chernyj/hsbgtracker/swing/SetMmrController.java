package chernyj.hsbgtracker.swing;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import chernyj.hsbgtracker.entity.User;
import chernyj.hsbgtracker.model.Player;
import chernyj.hsbgtracker.service.UserService;
import chernyj.hsbgtracker.utils.ApplicationConfiguration;
import chernyj.hsbgtracker.utils.observers.SetMmrObserver;
import chernyj.hsbgtracker.utils.subjects.SetMmrSubject;

public class SetMmrController implements SetMmrSubject {
	
	private List<SetMmrObserver> observers = new ArrayList<>();
	
	private SetMmrDialog dialog;
	private User user;
	
	public SetMmrController(SetMmrDialog dialog, Player player) {
		User user = findUser(player);
		init(dialog, user);
	}
	
	public SetMmrController(SetMmrDialog dialog, User user) {
		init(dialog, user);
	}
	
	private void init(SetMmrDialog dialog, User user) {
		this.dialog = dialog;
		
		this.user = user;

		updateStartMmr();
		
		setListeners();
	}
	
	private User findUser(Player player) {
		String name = player.getName();
		String btag = player.getbTag();
		
		UserService userService = new UserService();
		
		User userFromDb = userService.getByNameAndBTag(name, Integer.parseInt(btag));
		
		return (userFromDb == null) ? createNewUser(player) : userFromDb;
		
	}
	
	private User createNewUser(Player player) {
		User user = new User();
		user.setName(player.getName());
		user.setbTag(Integer.parseInt(player.getbTag()));
		return user;
		
	}
	
	private void updateStartMmr() {
		if(user != null)
			dialog.getTfMMR().setText(String.valueOf(user.getMmr()));
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
		
		updateUserInfo(mmrInt);
		
		notifyObservers(mmrInt);
		
		dialog.dispose();
		
	}
	
	private void updateUserInfo(int mmr) {
		user.setMmr(mmr);
		UserService userService = new UserService();
		userService.update(user);
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
