package me.calendar.component;

import java.awt.Font;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.BorderFactory;
import javax.swing.JButton;

import me.calendar.service.ActionDispatcher;
import me.calendar.service.ActionType;
import me.calendar.service.Utility;

public class DefaultButton extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3885624597780748507L;
	static Map<ActionType,String> textMap = new HashMap<ActionType,String>();
	static Font font  = Utility.cn11;
	static{
		textMap.put(ActionType.Query, "²éÑ¯");
		textMap.put(ActionType.Clear,"Çå³ý");
		textMap.put(ActionType.Save, "´¢´æ");
		font = Utility.cn11;
	}
	
	int width=40;
	int height=20;
	int x;
	int y=231;
	String texts = "";
	ActionType type;
	
	
	public DefaultButton(ActionType action)
	{
		init(action);
	}
	
	public DefaultButton(Icon ii,ActionType action)
	{
		super(ii);
		init(action);
		
	}
	
	public void init(ActionType action)
	{
		type=action;
		texts = textMap.get(action);
		setText(texts);
		setFont(font);
		setBorder(BorderFactory.createTitledBorder(""));
		addActionListener(new ActionDispatcher(type));
		setSize(width, height);
	}
	
	
	public void setX(int _x)
	{
		x=_x;
		setBounds(x, y, width, height);
	}	
	
	
	

}
