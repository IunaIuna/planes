#ifndef PLANESWINDOW_H
#define PLANESWINDOW_H

#include <QMainWindow>
#include "planeswview.h"
#include "planesmodel.h"
#include "planeroundjavafx.h"


//Main window of the application
class PlanesWWindow : public QMainWindow
{
    Q_OBJECT

    //The view object
    PlanesWView* mPlanesView;
    //The model object
    PlanesModel* mPlanesModel;
    //The controller object
    PlaneRoundJavaFx *mRound;

public:
    explicit PlanesWWindow(QWidget *parent = 0);
    ~PlanesWWindow();
};

#endif // PLANESWINDOW_H
