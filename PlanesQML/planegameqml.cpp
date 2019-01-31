#include "planegameqml.h"

PlaneGameQML::PlaneGameQML()
{
    //builds the game object - the controller
    mRound = new PlaneRound(10, 10, 3);
    connect(this, SIGNAL(guessMade(const GuessPoint&)), this, SLOT(receivedPlayerGuess(const GuessPoint&)));
    mRound->initRound();
	emit resetGrid();
}

PlaneGameQML::~PlaneGameQML() {
	delete mRound;
}

void PlaneGameQML::doneEditing() {
	mRound->doneEditing();
}

void PlaneGameQML::statsUpdated(const GameStatistics& stats) {
    m_Stats = stats;
    emit updateStats();
}

void PlaneGameQML::startNewGame() {
	printf("Start new round\n");
	if (mRound->didRoundEnd()) {
		mRound->initRound();
		emit resetGrid();
		statsUpdated(GameStatistics());
	}
}
void PlaneGameQML::receivedPlayerGuess(const GuessPoint& gp)
{
	PlayerGuessReaction pgr;
	mRound->playerGuess(gp, pgr);

	if (pgr.m_ComputerMoveGenerated) {
		emit computerMoveGenerated(pgr.m_ComputerGuess);
	}
	if (pgr.m_RoundEnds) {
		emit roundEnds(!pgr.m_isPlayerWinner);
		mRound->roundEnds();
	}

	statsUpdated(pgr.m_GameStats);
}
