#include "planesgswindow.h"

PlanesGSWindow::PlanesGSWindow(QWidget *parent) : QMainWindow(parent)
{
    //builds the game object - the controller
    mRound = new PlaneRound(10, 10, 3);

    m_UserData = new UserData();    
    m_NetworkManager = new QNetworkAccessManager(this);
    m_GameInfo = new GameInfo();
    
    //builds the view object
    mPlanesView = new PlanesGSView(mRound, m_UserData, m_NetworkManager, m_GameInfo);
    setCentralWidget(mPlanesView);
}
