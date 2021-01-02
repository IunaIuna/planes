#ifndef PLANESGSWINDOW_H
#define PLANESGSWINDOW_H

#include <QMainWindow>
#include <QNetworkAccessManager>
#include "planesgsview.h"
#include "planeround.h"
#include "global/globaldata.h"
#include "gameinfo.h"

class PlanesGSWindow : public QMainWindow
{
    Q_OBJECT
public:
    explicit PlanesGSWindow(QWidget *parent = 0);

signals:

public slots:

private:
    //The view object
    PlanesGSView* mPlanesView;
    //The game object : playing against the computer
    PlaneRound* mRound;
    //TODO: The game object: multiplayer
    //User data for the multiplayer game
    GlobalData* m_GlobalData;
    QNetworkAccessManager* m_NetworkManager;
    GameInfo* m_GameInfo;
};

#endif // PLANESGSWINDOW_H
