import React from 'react'
import { Route } from 'react-router-dom'

export default function Barcode() {
	return (
		<div>
			<h1>Barcode</h1>
			<Route path='/barcode' element={<Barcode />} />
		</div>
	)
}
