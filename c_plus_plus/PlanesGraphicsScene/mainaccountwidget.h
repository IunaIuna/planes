#ifndef _MAIN_ACCOUNT_WIDGET__
#define _MAIN_ACCOUNT_WIDGET__

#include <QWidget>
#include <QNetworkAccessManager>
#include <QSettings>
#include <QJsonObject>
#include "userdata.h"
#include "gameinfo.h"


class MainAccountWidget : public QWidget {
    Q_OBJECT
    
public:    

    MainAccountWidget(QSettings* settings, UserData* userData, QNetworkAccessManager* m_NetworkManager, GameInfo* gameInfo, QWidget* parent = nullptr);

signals:
    void noRobotRegistration(const std::vector<QString>& images, const QJsonObject& request);
    
private:
    UserData* m_UserData;
    QNetworkAccessManager* m_NetworkManager;
    QSettings* m_Settings;
    GameInfo* m_GameInfo;

};


#endif
