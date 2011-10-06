package org.eclipse.jetty.nosql.session.kryo;

import org.eclipse.jetty.nosql.session.*;

public class KryoSessionFacade extends AbstractSessionFacade {
	public KryoSessionFacade() {
		sessionFactory = new KryoSessionFactory();
		transcoder = new KryoTranscoder();
	}

	@Override
	public byte[] pack(ISerializableSession session, ISerializationTranscoder tc) throws TranscoderException {
		byte[] raw = null;
		try {
			raw = tc.encode(session);
		} catch (Exception error) {
			throw(new TranscoderException(error));
		}
		return raw;
	}

	@Override
	public ISerializableSession unpack(byte[] raw, ISerializationTranscoder tc) throws TranscoderException {
		ISerializableSession session = null;
		try {
			session = tc.decode(raw, KryoSession.class);
		} catch (Exception error) {
			throw(new TranscoderException(error));
		}
		return session;
	}
}
