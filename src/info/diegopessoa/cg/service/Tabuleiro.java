/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.diegopessoa.cg.service;

import info.diegopessoa.cg.sprite.Character;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 *
 * @author diegopessoa
 */
public class Tabuleiro<T> {

	private Map<Point, List<T>> positions;
	private int maxRows;
	private int maxColumns;

	public Tabuleiro (int rows, int columns) {
		positions = new HashMap<Point, List<T>>();
		maxRows = rows;
		maxColumns = columns;
		init();
	}

	public final void init() {
		for (int i = 0 ; i < maxRows ; i++) {
			for (int j = 0 ; j < maxColumns ; j++) {
				positions.put(new Point(i, j), null);
			}
		}
	}

	public void setposition(int row, int column, T t) {
		if (isOutOfSpace(row, column)) throw new RuntimeException("Posições fora do limite máximo.");
		List<T> elementos = positions.get(new Point(row, column));
		if (elementos == null || elementos.isEmpty()) {
			List<T> lista = new ArrayList<T>();
			lista.add(t);
			positions.put(new Point(row, column), lista);
		} else elementos.add(t);
	}

	public void clearPosition(int row, int column) {
		if (isOutOfSpace(row, column)) throw new RuntimeException("Posições fora do limite máximo.");
		List<T> elements = positions.get(new Point(row, column));
		if (elements != null) elements.clear();
	}
	
	public void clearPosition(T t) {
		Point p = getPosition(t);
		if (isOutOfSpace(p.x, p.y)) throw new RuntimeException("Posições fora do limite máximo.");
		List<T> elements = positions.get(p);
		if (elements != null) {
			Iterator<T> elementsIterator = elements.iterator();
			while (elementsIterator.hasNext()) {
				T element = elementsIterator.next();
				if (element.equals(t)) elementsIterator.remove();
			}
		}
	}

	public boolean isFree(int row, int column) {
		if (isOutOfSpace(row, column)) throw new RuntimeException("Posições fora do limite máximo.");
		return positions.get(new Point(row, column)) == null || positions.get(new Point(row, column)).isEmpty();
	}

	public List<T> getObjects(int row, int column) {
		if (isOutOfSpace(row, column)) throw new RuntimeException("Posições fora do limite máximo.");
		return positions.get(new Point(row, column));
	}

	public Point getPosition(T t) {
		Iterator<Point> points = positions.keySet().iterator();
		while (points.hasNext()) {
			Point point = points.next();
			List<T> elements = positions.get(point);
			if (elements != null) {
				for (T element : elements) {
					if (element.equals(t)) return point;
				}
			}
		} return null;
	}

	public List<T> getNextPosition(int row, int column, int direcao) {
		List<T> objs = null;
		switch (direcao) {
			case Character.DIRECAO_NORTE: {
				objs = getObjects(row, column+1);
				break;
			}
			case Character.DIRECAO_SUL: {
				objs = getObjects(row, column-1);
				break;
			}
			case Character.DIRECAO_LESTE: {
				objs = getObjects(row+1, column);
				break;
			}
			case Character.DIRECAO_OESTE: {
				objs = getObjects(row-1, column);
				break;
			}
		}
		return objs;
	}

	public List<T> getNextPosition(T t, int direcao) {
		Point ponto = getPosition(t);
		return getNextPosition(ponto.x, ponto.y, direcao);
	}

	public Point getNextPoint(T t, int direcao) {
		Point nextPoint = null;
		Point point = getPosition(t);
		if (point != null) {
			switch (direcao) {
				case Character.DIRECAO_NORTE: {
					nextPoint = new Point(point.x, point.y+1);
					break;
				}
				case Character.DIRECAO_SUL: {
					nextPoint = new Point(point.x, point.y-1);
					break;
				}
				case Character.DIRECAO_LESTE: {
					nextPoint = new Point(point.x+1, point.y);
					break;
				}
				case Character.DIRECAO_OESTE: {
					nextPoint = new Point(point.x-1, point.y);
					break;
				}
			}
		}
		return nextPoint;
	}

	public void move(T t, int direcao) {
		Point ponto = getPosition(t);
		switch (direcao) {
		case Character.DIRECAO_NORTE: {
			clearPosition(t);
			List<T> list = positions.get(new Point(ponto.x, ponto.y+1));
			if (list == null) {
				list = new ArrayList<T>();
				positions.put(new Point(ponto.x, ponto.y+1), list);
			}
			list.add(t);
			break;
		}
		case Character.DIRECAO_SUL: {
			clearPosition(t);
			List<T> list = positions.get(new Point(ponto.x, ponto.y-1));
			if (list == null) {
				list = new ArrayList<T>();
				positions.put(new Point(ponto.x, ponto.y-1), list);
			}
			list.add(t);
			break;
		}
		case Character.DIRECAO_LESTE: {
			clearPosition(t);
			List<T> list = positions.get(new Point(ponto.x+1, ponto.y));
			if (list == null) {
				list = new ArrayList<T>();
				positions.put(new Point(ponto.x+1, ponto.y), list);
			}
			list.add(t);
			break;
		}
		case Character.DIRECAO_OESTE: {
			clearPosition(t);
			List<T> list = positions.get(new Point(ponto.x-1, ponto.y));
			if (list == null) {
				list = new ArrayList<T>();
				positions.put(new Point(ponto.x-1, ponto.y), list);
			}
			list.add(t);
			break;
		}
		}
	}

	public boolean isOutOfSpace(int x, int y) {
		return x >= maxColumns || y >= maxRows || x < 0 || y < 0;
	}

	public boolean isFreeSpace(T t, int direcao) {
		Point ponto = getNextPoint(t, direcao);
		if (isOutOfSpace(ponto.x, ponto.y)){
			return false;
		} else if (!isFree(ponto.x, ponto.y)) {
			return false;
		}
		return true;
	}

}
