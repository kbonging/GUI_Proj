package com;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Main extends JFrame {
	// 총 금액을 표시할 레이블 (선택한 메뉴와 수량에 따라 금액을 변경하여 레이블로 표시를 하기 위해 전역 변수)
    private JLabel totalPriceLabel;  
    // 선택된 메뉴들을 저장할 리스트 
    private List<MenuInfo> selectedMenus = new ArrayList<>(); 
    // 메뉴 정보가 표시될 패널 (메뉴 선택 시 오른쪽 메뉴정보들이 초기화되고 다시 목록 보여주기위해 전역변수)
    private JPanel orderInfoPanel; 
    // 메뉴 버튼을 저장할 리스트 (추 후에 "주문하기"와 "초기화" 버튼 클릭 시 해당 버튼 다시 활성화를 하기위해 리스트에 저장 함)
    private List<JButton> menuButtons = new ArrayList<>(); 
    // 흰색
    Color whiteColor = new Color(255, 255, 255); 
    // 회색
    Color grayColor = new Color(220, 220, 220);
    // 어두운 회색
    Color darkGrayColor = new Color(50, 50, 50);
    
    public Main() { 
        setTitle("봉카페");
        // JFrame 기본 레이아웃을 BorderLayout으로 설정하고 여백 줌
        setLayout(new BorderLayout(10, 10));

        // 왼쪽 패널 호출
        showWest();
        // 오른쪽 패널 호출
        showEast();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1050, 700);
        setLocationRelativeTo(null); // 화면 중앙에 배치
        setVisible(true);
    }

    // 왼쪽 패널
    void showWest() {
    	// 메뉴를 표시할 메인 패널 생성
        JPanel panel = new JPanel();
        
		/* 패널 레이아웃을 GridLayout으로 설정 행은 자동 4열로 */ 
        panel.setLayout(new GridLayout(0, 4, 3, 3));
        
        // 메뉴 정보 배열 생성
        MenuInfo[] menuInfos = {
            new MenuInfo("아메리카노[ICE]", 4000, "americano.jpg"),
            new MenuInfo("아메리카노[HOT]", 4000, "HOTAmericano.jpg"),
            new MenuInfo("카페라떼[ICE]", 5000, "cafeLatte.jpg"),
            new MenuInfo("카페라떼[HOT]", 5000, "HOTCafeLatte.jpg"),
            new MenuInfo("딸기라떼", 7500, "StrawberryLatte.jpg"),
            new MenuInfo("초코라떼", 7500, "ChocolateLatte.jpg"),
            new MenuInfo("레드벨벳", 10000, "cake1.jpg"),
            new MenuInfo("초코케이크", 10000, "cake2.jpg"),
            new MenuInfo("물", 6000, "water.png")
        };

        for (int i = 0; i < menuInfos.length; i++) { // 등록된 메뉴만큼 돌림
            final int index = i;  // 람다식을 사용하기 위해 i 값을 final로 처리
            // 각 메뉴버튼을 패널에 넣음(이렇게 패널에 넣어야 간격을 맞출 수 있음)
            JPanel menuPanel = new JPanel(); 
            //////////////////////////////////////////////
            //////////// 버튼 이미지 넣기 시작 /////////////
            //////////////////////////////////////////////
            // 원본 이미지 로드
            ImageIcon originalIcon = new ImageIcon(Main.class.getResource("/com/"+menuInfos[i].getImage()));
            
            // 이미지 크기 조정
            Image originalImage = originalIcon.getImage();
            Image resizedImage = originalImage.getScaledInstance(150, 150, Image.SCALE_SMOOTH); // 크기 변경
            
            // 크기 변경된 이미지를 새로운 ImageIcon으로 생성
            ImageIcon resizedIcon = new ImageIcon(resizedImage);
            
            // 메뉴 버튼 생성 (이미지와 가격, 상품명을 함께 표시)
            JButton menuButton = new JButton();
            menuButton.setBackground(whiteColor);
            menuButton.setLayout(new BoxLayout(menuButton, BoxLayout.Y_AXIS));  // 수직 레이아웃 설정
            
            // 이미지 부분 (상단에 이미지)
            JLabel imageLabel = new JLabel(resizedIcon);
            menuButton.add(imageLabel);  // 버튼에 이미지 추가
            
            // 간격 추가
            menuButton.add(Box.createVerticalStrut(10));  // 이미지와 가격 사이에 10px 간격 추가
            
            // 하단 이름과 가격 부분임
            JLabel priceLabel = new JLabel("<html><center>" + menuInfos[i].getName() + "<br><br>" 
            	    + String.format("%,d", menuInfos[i].getPrice()) + "원</center></html>");
            priceLabel.setHorizontalAlignment(SwingConstants.CENTER);  // 가로로 가운데 정렬
            priceLabel.setVerticalAlignment(SwingConstants.CENTER);    // 세로로 가운데 정렬
            
            menuButton.add(priceLabel);  // 버튼에 가격 텍스트 추가
            //////////////////////////////////////////////
            //////////// 버튼 이미지 넣기 끝 /////////////
            //////////////////////////////////////////////
//            // 버튼 생성 (이거 안씀 추후에 지우기)
//            JButton menuButton = new JButton("<html><center>" + menuInfos[i].getName() + "<br><br>" + String.format("%,d", menuInfos[i].getPrice()) + "원</center></html>");

            // 텍스트를 버튼 안에서 수평, 수직으로 가운데 정렬 (이거 안씀 추후에 지우기)
//            menuButton.setHorizontalAlignment(SwingConstants.CENTER);
//            menuButton.setVerticalAlignment(SwingConstants.CENTER);
            menuButton.setPreferredSize(new Dimension(150, 220)); // 각 메뉴 버튼의 크기 고정

            // 버튼 리스너
            menuButton.addActionListener(e -> updateOrderInfo(menuInfos[index])); // 버튼 클릭 시 주문리스트 갱신하는 리스너
            menuButton.addActionListener(e -> {
            	JButton sourceButton= (JButton)e.getSource(); // 해당 버튼객체를 가져옴
            	sourceButton.setEnabled(false); // 해당 버튼 비활성화
            	sourceButton.setBackground(grayColor); // 비활성화 색상변경
            });
            
            // 메뉴 버튼을 리스트에 저장
            menuButtons.add(menuButton);
            
            menuPanel.add(menuButton);
            panel.add(menuPanel);
        }

        // 스크롤을 추가하기 위해 JScrollPane에 패널을 추가
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // 세로 스크롤바 항상 보이기
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // 가로 스크롤바는 보이지 않도록 설정
        scrollPane.setPreferredSize(new Dimension(700, getHeight())); // 높이는 프레임에 맞추기
        add(scrollPane, BorderLayout.WEST);  // BorderLayout의 WEST에 스크롤패널 추가
    }

    // 선택된 메뉴 정보를 오른쪽 패널에 업데이트
    private void updateOrderInfo(MenuInfo selectedMenu) {
        selectedMenus.add(selectedMenu);  // 선택된 메뉴를 리스트에 추가
        
        refreshOrderInfoPanel();  // 오른쪽 패널 갱신
        updateTotalPrice();  // 총 금액 업데이트
    }

    // 오른쪽 패널
    void showEast() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10)); // 여백을 설정한 레이아웃
        panel.setPreferredSize(new Dimension(335, 100)); // 오른쪽 패널 크기조정

        // 주문 정보 영역 패널
        orderInfoPanel = new JPanel();
        orderInfoPanel.setLayout(new BoxLayout(orderInfoPanel, BoxLayout.Y_AXIS)); // 세로로 쌓기
        // 주문 정보 영역을 오른쪽 패널에 추가
        panel.add(orderInfoPanel, BorderLayout.NORTH);
        
        // 하단에 주문하기 버튼이랑 총금액 정보 패널
        JPanel totalPrice_OrderBtnPanel = new JPanel(new GridLayout(3,0));
        JPanel totalPriceDescPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        totalPriceDescPanel.setBackground(grayColor); // 배경 흰색
        JPanel totalPricePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        totalPricePanel.setBackground(grayColor); // 배경 흰색
        JPanel oderBtnPanel = new JPanel();
        oderBtnPanel.setBackground(grayColor);
        
        // 총 주문금액 설명란임
        totalPriceDescPanel.add(new JLabel("총 주문 금액"));
        
        // 총 금액은 전역변수로 설정 함
        totalPriceLabel = new JLabel("0원");
        totalPricePanel.add(totalPriceLabel);
        
        // 주무하기 버튼
        JButton orderBtn = new JButton("주문하기");
        // 주문하기 색상변경
        orderBtn.setBackground(darkGrayColor); // 배경색 설정 (다크 그레이)
        orderBtn.setForeground(new Color(240, 240, 240)); // 텍스트 색상 설정
        oderBtnPanel.add(orderBtn);
        // 주문하기 버튼 리스너
        orderBtn.addActionListener(e -> handleOrderButton());
        
        // 선택취소(초기화) 버튼
        JButton resetBtn = new JButton("선택취소");
        // 선택취소 버튼 색갈
        resetBtn.setBackground(new Color(240, 240, 240));
        resetBtn.setForeground(darkGrayColor); // (다크 그레이)
        oderBtnPanel.add(resetBtn);
        resetBtn.addActionListener(e -> resetMenuButtons());
        
        // 총 금액 패널이랑 주문하기 패널을 또 패널로 넣음 (오른쪽 패널 하단에 넣기위해 하는거)
        totalPrice_OrderBtnPanel.add(totalPriceDescPanel);
        totalPrice_OrderBtnPanel.add(totalPricePanel);
        totalPrice_OrderBtnPanel.add(oderBtnPanel);
        panel.add(totalPrice_OrderBtnPanel, BorderLayout.SOUTH);

        add(panel, BorderLayout.EAST);  // BorderLayout의 EAST에 오른쪽 패널 추가
    }



     // 선택된 메뉴들의 정보를 갱신하는 메서드
    private void refreshOrderInfoPanel() {
        orderInfoPanel.removeAll();  // 기존의 메뉴 정보들 제거
        
        // 제목 라벨
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel orderTitle = new JLabel("<html>메뉴명&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                + "가격&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                + "수량&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</html>");
        titlePanel.add(orderTitle);
        orderInfoPanel.add(titlePanel);

        // 각 메뉴에 대한 정보 추가
        for (MenuInfo menu : selectedMenus) {
            JPanel menuPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));  // 오른쪽 정렬
            
            int price = menu.getPrice(); // 해당 제품 가격 가져오기
            int quantity = menu.getQuantity(); // 해당 메뉴 수량 가져오기(초기 수량은 1임)
            
            // 메뉴 이름 레이블
            JLabel menuNameLabel = new JLabel(menu.getName());
            // 메뉴 가격 레이블
            JLabel menuPriceLabel = new JLabel(String.format("%,d", (price*quantity)) + "원");
            
            JButton minusBtn = new JButton("-");
            minusBtn.setBackground(grayColor);
            JButton plusBtn = new JButton("+");
            plusBtn.setBackground(grayColor);
            JTextField quantityField = new JTextField(Integer.toString(quantity), 2);// 문자열로 번환
            
            // 수량 필드에서 금액 갱신
           // quantityField.addActionListener(e -> updateTotalPrice()); 이거 안씀 추후에 지우기
            quantityField.setEditable(false); // 텍스트박스를 읽기전용으로 하는거임 (enable)은 흐려짐
            
            // + 버튼 리스너
            plusBtn.addActionListener(e -> {
                menu.setQuantity(menu.getQuantity() + 1); // 기존 수량 +1 해서 setter로 값 넣음
                quantityField.setText(String.valueOf(menu.getQuantity())); // getter로 가져와서 형변환
                menuPriceLabel.setText(String.format("%,d", menu.getPrice() * menu.getQuantity()) + "원");
                updateTotalPrice(); // 총 금액 업데이트
            });

            // - 버튼 리스너
            minusBtn.addActionListener(e -> {
                if (menu.getQuantity() > 1) { // 수량이 1 이하로 줄어들지 않도록 제한
                    menu.setQuantity(menu.getQuantity() - 1);
                    quantityField.setText(String.valueOf(menu.getQuantity()));
                    menuPriceLabel.setText(String.format("%,d", menu.getPrice() * menu.getQuantity()) + "원");
                    updateTotalPrice(); // 총 금액 업데이트
                }
            });

            menuPanel.add(menuNameLabel);
            menuPanel.add(menuPriceLabel);
            menuPanel.add(minusBtn);
            menuPanel.add(quantityField);
            menuPanel.add(plusBtn);
            
            orderInfoPanel.add(menuPanel);
        }

        updateTotalPrice(); // 총 금액 레이블 갱신
        // 레이아웃을 변경하거나 추가/삭제된 컴포넌트를 반영하고자 할 때, 이 두 메서드를 호출하여 GUI를 갱신합니다.
        orderInfoPanel.revalidate();
        orderInfoPanel.repaint();
    }


    // 수량에 맞는 총 금액을 업데이트하는 메서드
    private void updateTotalPrice() {
        int totalPrice = 0;
        for (MenuInfo menu : selectedMenus) {
        	int price =menu.getPrice();
        	int quantity = menu.getQuantity();
        	totalPrice+= price * quantity;
        }
        totalPriceLabel.setText(String.format("%,d", totalPrice) + "원"); // 총 금액 업데이트
    }
    
    // 주문하기 버튼 리스너를 처리하는 메서드
    private void handleOrderButton() {
    	// selectedMenus가 비어있는지 체크
        if (selectedMenus.isEmpty()) {
            // 메뉴가 선택되지 않았다면 안내 메시지를 출력
            JOptionPane.showMessageDialog(this, "메뉴를 선택하세요.", "알림", JOptionPane.WARNING_MESSAGE);
            return; // 메서드 종료
        }
    	
        // 팝업을 띄우고, 메뉴 목록을 보여주거나 주문 완료 메시지를 출력하는 로직
        StringBuilder orderSummary = new StringBuilder();
        for (MenuInfo menu : selectedMenus) {
            int price = menu.getPrice();
            int quantity = menu.getQuantity();
            orderSummary.append(menu.getName())
                        .append("  ")
                        .append(quantity)
                        .append("개  ")
                        .append(String.format("%,d", price * quantity))
                        .append("원\n");
        }

        // 총 금액을 마지막에 추가
        int totalPrice = 0;
        for (MenuInfo menu : selectedMenus) {
            totalPrice += menu.getPrice() * menu.getQuantity();
        }
        orderSummary.append("\n총 금액: ").append(String.format("%,d", totalPrice)).append("원");
        
        // 주문 완료 팝업창 띄우기
        JOptionPane.showMessageDialog(this, orderSummary.toString(), "주문 완료", JOptionPane.INFORMATION_MESSAGE);

        resetMenuButtons(); 
    }
    
    // 메뉴 버튼 초기화 메서드
    private void resetMenuButtons() {
        // 메뉴 버튼들을 모두 활성화
        for (JButton button : menuButtons) {
            button.setEnabled(true);
            button.setBackground(whiteColor);
        }
        // 메뉴선택 수량 초기화하기
        for (MenuInfo menu : selectedMenus) {
        	menu.setQuantity(1);
        }
        // 선택된 메뉴 리스트 초기화
        selectedMenus.clear(); // 메뉴 목록 초기화
        refreshOrderInfoPanel(); // 오른쪽 패널 갱신 (리스트에 아무것도없는 상태 갱싱)
        updateTotalPrice(); // 총 금액 초기화 (리스트에 아무것도 없으니 가격도 0원으로 나옴)
    }

    public static void main(String[] args) {
        new Main();
    }
}
