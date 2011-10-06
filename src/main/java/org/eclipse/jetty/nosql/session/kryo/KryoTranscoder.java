package org.eclipse.jetty.nosql.session.kryo;

import org.eclipse.jetty.nosql.session.ISerializationTranscoder;
import org.eclipse.jetty.nosql.session.TranscoderException;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.ObjectBuffer;

public class KryoTranscoder implements ISerializationTranscoder {
	private Kryo kryo = null;
	public KryoTranscoder() {
		kryo = new Kryo();
		kryo.setRegistrationOptional(true);
	}

	public byte[] encode(Object obj) throws TranscoderException {
		byte[] raw = null;
		try {
			ObjectBuffer buf = new ObjectBuffer(kryo);
			raw = buf.writeObject(obj);
		} catch (Exception error) {
			throw(new TranscoderException(error));
		}
		return raw;
	}

	public <T> T decode(byte[] raw, Class<T> klass) throws TranscoderException {
		T obj = null;
		try {
		ObjectBuffer buf = new ObjectBuffer(kryo);
		obj = buf.readObject(raw, klass);
		} catch (Exception error) {
			throw(new TranscoderException(error));
		}
		return obj;
	}
}
