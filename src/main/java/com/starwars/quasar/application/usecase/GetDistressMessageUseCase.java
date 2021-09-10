package com.starwars.quasar.application.usecase;

import java.util.List;

import com.starwars.quasar.domain.mapper.DistancesDataMapper;
import com.starwars.quasar.domain.mapper.MessagesDataMapper;
import com.starwars.quasar.domain.mapper.PositionsDataMapper;
import com.starwars.quasar.domain.model.Position;
import com.starwars.quasar.domain.model.Satelite;
import com.starwars.quasar.domain.repository.SatelliteRepository;
import com.starwars.quasar.domain.services.Locator;
import com.starwars.quasar.domain.services.LocatorFactory;
import com.starwars.quasar.domain.services.MessageDecryptor;
import com.starwars.quasar.domain.services.SatellitesFinder;
import com.starwars.shared.UseCase;

/**
 * Clase del servicio de inteligencia que se encarga de obtener 
 * la posición y el descifrado del mensaje de la nave.
 * 
 * @author Federico Farias Sánchez
 *
 */
@UseCase
public class GetDistressMessageUseCase {

	private final MessageDecryptor decryptor;
	
	private final SatellitesFinder satellitesFinder;

	private final PositionsDataMapper positionsDataMapper;

	private final MessagesDataMapper messagesDataMapper;

	private final DistancesDataMapper distancesDataMapper;

	public GetDistressMessageUseCase(SatelliteRepository setelliteRepository, MessageDecryptor decryptor) {
		this.satellitesFinder = new SatellitesFinder(setelliteRepository);
		this.decryptor = decryptor;
		this.positionsDataMapper = new PositionsDataMapper();
		this.messagesDataMapper = new MessagesDataMapper();
		this.distancesDataMapper = new DistancesDataMapper();
	}

	/**
	 * Método para calcular la posición el descifrar el mensaje de la nave.
	 * 
	 * @param request Mensaje de auxilio de la nave.
	 * @return Posición y mensaje descifrado. 
	 */
	public DecryptedMessageResponse execute(DistressMessageRequest request) {
		List<SatelliteRequest> satellitesRequest = request.getSatellites();
		
		List<Satelite> satellites = this.satellitesFinder.findByName(satellitesRequest);
		List<Position> satellitesPositions = this.positionsDataMapper.getPositions(satellites);
		Locator locator = LocatorFactory.getInstance(satellitesPositions);
		
		String[][] messages = this.messagesDataMapper.getMessages(satellitesRequest);
		double[] distances = this.distancesDataMapper.getDistances(satellitesRequest);

		Position position = locator.getLocation(distances);
		String message = this.decryptor.getMessage(messages);

		return new DecryptedMessageResponse(new DecryptedPositionResponse(position.getX(), position.getY()), message);
	}

}
